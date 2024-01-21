package ba.ibu.edu.web_engineering_project.rest.dto;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.TicketEvent;
import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;

import java.util.Date;

public class TicketRequestDTO {
    private TicketType ticketType;
    private double price;
    private String buyerId;
    private TicketEvent event;


    public TicketRequestDTO(){}

    public TicketRequestDTO(Ticket ticket){
        this.ticketType = ticket.getTicketType();
        this.price = ticket.getPrice();
        this.buyerId = ticket.getBuyerId();
        this.event = ticket.getEvent();
    }

    public Ticket toEntity(){
        Buyer buyer = new Buyer();
        buyer.setId(this.buyerId);

        Ticket ticket = new Ticket();
        ticket.setTicketType(this.ticketType);
        ticket.setPrice(this.price);
        ticket.setBuyerId(this.buyerId);
        ticket.setEvent(this.event);
        return ticket;
    }


    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public TicketEvent getEvent() {
        return event;
    }

    public void setEvent(TicketEvent event) {
        this.event = event;
    }
}
