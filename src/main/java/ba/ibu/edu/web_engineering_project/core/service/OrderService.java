package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.api.impl.mailsender.AsyncMailSender;
import ba.ibu.edu.web_engineering_project.api.impl.mailsender.GmailSMTPSender;
import ba.ibu.edu.web_engineering_project.api.impl.qr.QRGenerator;
import ba.ibu.edu.web_engineering_project.api.lambda.GenerateAndMailTicketsLambda;
import ba.ibu.edu.web_engineering_project.core.exceptions.event.InvalidEventStatusException;
import ba.ibu.edu.web_engineering_project.core.exceptions.order.TicketPurchaseException;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.Order;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.embedded.OrderBoughtTicket;
import ba.ibu.edu.web_engineering_project.core.model.embedded.SeatsPerTicketType;
import ba.ibu.edu.web_engineering_project.core.model.embedded.TicketEvent;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import ba.ibu.edu.web_engineering_project.core.repository.OrderRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.*;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final GenerateAndMailTicketsLambda generateAndMailTicketsLambda;
    //private final QRGenerator qrGenerator;
    //private final AsyncMailSender asyncMailSender;

    public OrderService(OrderRepository orderRepository, UserService userService, TicketService ticketService, EventService eventService, QRGenerator qrGenerator, GmailSMTPSender gmailSMTPSender, AsyncMailSender asyncMailSender, GenerateAndMailTicketsLambda generateAndMailTicketsLambda) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.generateAndMailTicketsLambda = generateAndMailTicketsLambda;
        //this.qrGenerator = qrGenerator;
        //this.asyncMailSender = asyncMailSender;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(String id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new ResourceNotFoundException("The order with the provided ID does not exist.");
        }
        return order.get();
    }

    public Order addOrder(OrderRequestDTO payload){
        return orderRepository.save(payload.toEntity());
    }

    public Order updateOrder(String id, OrderRequestDTO payload){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new ResourceNotFoundException("The order with the provided ID does not exist.");
        }
        Order updatedOrder = payload.toEntity();
        updatedOrder.setId(order.get().getId());
        updatedOrder = orderRepository.save(updatedOrder);
        return updatedOrder;
    }

    public void deleteOrder(String id){
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(orderRepository::delete);
    }

    public Order purchaseTickets(TicketPurchaseRequestDTO ticketPurchaseRequestDTO) throws IOException, WriterException {

        EventDTO event = eventService.getEventById(ticketPurchaseRequestDTO.getEventId());
        validateEventStatus(event);

        List<Ticket> tickets = new ArrayList<>();
        List<SeatsPerTicketType> seatsPerTicketTypeList = new ArrayList<>();
        double total = 0;

        for(SeatsPerTicketType seatsPerTicketType : event.getSeatsPerTicketType()){
            // Making a list to put inside the event after I update ticket numbers

            // Searching if there is that ticket type in the request
            // if not, put inside the list and continue,
            // If yes, check if there is available, make tickets, put seats inside the list
            TicketPurchaseTypeQuantityDTO ticketType = findTicketPurchaseTypeQuantity(ticketPurchaseRequestDTO.getTicketTypes(),seatsPerTicketType);

            if(ticketType != null){
                if(seatsPerTicketType.getQuantity() < ticketType.getQuantity()){
                    throw new TicketPurchaseException("Order denied. Insufficient number of tickets for " + seatsPerTicketType.getTicketType() + " ticket.");
                }
                seatsPerTicketType.setQuantity(seatsPerTicketType.getQuantity() - ticketType.getQuantity());
                for(int i = 0; i < ticketType.getQuantity(); i++){
                    TicketEvent ticketEvent = new TicketEvent();
                    ticketEvent.setId(event.getId());
                    ticketEvent.setTitle(event.getName());
                    ticketEvent.setLocation(event.getLocation());

                    Ticket ticket = new Ticket();
                    ticket.setTicketType(ticketType.getTicketType());
                    ticket.setPrice(seatsPerTicketType.getTypePrice());
                    total += seatsPerTicketType.getTypePrice();
                    ticket.setEvent(ticketEvent);
                    ticket.setBuyerId(ticketPurchaseRequestDTO.getUserId());
                    tickets.add(ticket);
                }
            }

            seatsPerTicketTypeList.add(seatsPerTicketType);
        }

        tickets = ticketService.addAllTickets(tickets);

        Event event1 = createEvent(event, seatsPerTicketTypeList);
        eventService.orderUpdateEvent(event1);
        UserDTO buyer = userService.getUserById(ticketPurchaseRequestDTO.getUserId());

        // Generating QR codes and sending to user ** NOT NECESSARY NOW BECAUSE OF LAMBDA THAT IS MADE
        /*for(Ticket t : tickets){
            qrGenerator.generateTicketQR(t.getId(), t.getBuyerId(), t.getEvent().getTitle(), t.getTicketType().name());
        }
        // this is a method for sending generated QR codes but is also replaced by lambda
        asyncMailSender.sendQRTicketsAsync(userService.getUserById(ticketPurchaseRequestDTO.getUserId()).getEmail(), tickets);
        */

        //New implementation of the previous method ( with custom AWS lambda )
        System.out.println("sending to lambda");
        generateAndMailTicketsLambda.generateQRAndEmailTickets(buyer.getId(), buyer.getName(), buyer.getEmail(), tickets);
        System.out.println("lambda done");

        Order order = new Order();
        order.setBuyerId(ticketPurchaseRequestDTO.getUserId());
        order.setBoughtTickets(tickets
                .stream()
                .map(OrderBoughtTicket::new)
                .collect(toList()));
        order.setTotalPrice(total);
        order.setPaymentType(ticketPurchaseRequestDTO.getPaymentType());

        return orderRepository.save(order);

    }

    private static Event createEvent(EventDTO event, List<SeatsPerTicketType> seatsPerTicketTypeList) {
        Event event1 = new Event();
        event1.setId(event.getId());
        event1.setName(event.getName());
        event1.setDescription(event.getDescription());
        event1.setLocation(event.getLocation());
        event1.setEventCategory(event.getEventCategory());
        event1.setOrganizer(event.getOrganizer());
        event1.setOccuranceDateTime(event.getOccuranceDateTime());
        event1.setEventStatus(event.getEventStatus());
        event1.setCreationDate(event.getCreationDate());
        event1.setSeatsPerTicketType(seatsPerTicketTypeList);
        return event1;
    }

    private void validateEventStatus(EventDTO event) {
        if (event.getEventStatus() != EventStatus.ONGOING) {
            throw new InvalidEventStatusException("Event is not ready for selling tickets.");
        }
    }

    private TicketPurchaseTypeQuantityDTO findTicketPurchaseTypeQuantity(List<TicketPurchaseTypeQuantityDTO> ticketPurchaseTypeQuantityDTOS, SeatsPerTicketType seatsPerTicketType){
        for(TicketPurchaseTypeQuantityDTO ticketPurchaseTypeQuantityDTO : ticketPurchaseTypeQuantityDTOS){
            if(ticketPurchaseTypeQuantityDTO.getTicketType() == seatsPerTicketType.getTicketType()){
                return ticketPurchaseTypeQuantityDTO;
            }
        }

        return null;
    }
}
