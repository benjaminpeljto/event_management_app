package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.repository.TicketRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.TicketRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;


    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(String id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            throw new ResourceNotFoundException("The ticket with the provided ID does not exist.");
        }
        return ticket.get();
    }

    public Ticket addTicket(TicketRequestDTO payload){
        return ticketRepository.save(payload.toEntity());
    }

    public List<Ticket> addAllTickets(List<Ticket> tickets){
        return ticketRepository.saveAll(tickets);
    }

    public Ticket updateTicket(String id, TicketRequestDTO payload){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            throw new ResourceNotFoundException("The ticket with the provided ID does not exist.");
        }
        Ticket updatedTicket = payload.toEntity();
        updatedTicket.setId(ticket.get().getId());
        updatedTicket = ticketRepository.save(updatedTicket);
        return updatedTicket;
    }

    public void deleteTicket(String id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.ifPresent(ticketRepository::delete);
    }


}
