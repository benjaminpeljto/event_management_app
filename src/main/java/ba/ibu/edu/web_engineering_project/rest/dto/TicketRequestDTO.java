package ba.ibu.edu.web_engineering_project.rest.dto;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;

import java.util.Date;

public class TicketRequestDTO {
    private TicketType ticketType;
    private double price;
    private String buyerId;
    private String buyerName;
    private String buyerEmail;
    private String eventId;
    private Date expiresAt;


    public TicketRequestDTO(){}

    public TicketRequestDTO(Ticket ticket){
        this.ticketType = ticket.getTicketType();
        this.price = ticket.getPrice();
        this.buyerId = ticket.getBuyer().getId();
        this.buyerName = ticket.getBuyer().getName();
        this.buyerEmail = ticket.getBuyer().getEmail();
        this.eventId = ticket.getEventId();
        this.expiresAt = ticket.getExpiresAt();
    }

    public Ticket toEntity(){
        Buyer buyer = new Buyer();
        buyer.setId(this.buyerId);
        buyer.setName(this.buyerName);
        buyer.setEmail(this.buyerEmail);

        Ticket ticket = new Ticket();
        ticket.setTicketType(this.ticketType);
        ticket.setPrice(this.price);
        ticket.setBuyer(buyer);
        ticket.setEventId(this.eventId);
        ticket.setExpiresAt(this.expiresAt);
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

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
