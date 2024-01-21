package ba.ibu.edu.web_engineering_project.core.model.embedded;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;

public class OrderBoughtTicket {
    private String Id;
    private TicketType ticketType;
    private double price;
    private String eventId;

    public OrderBoughtTicket(Ticket ticket){
        this.Id = ticket.getId();
        this.ticketType = ticket.getTicketType();
        this.price = ticket.getPrice();
        this.eventId = ticket.getEvent().getId();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
