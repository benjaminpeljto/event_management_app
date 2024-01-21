package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.TicketEvent;
import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Ticket {
    @Id
    private String id;
    private TicketType ticketType;
    private double price;
    private String buyerId;
    private TicketEvent event;
    private Date createdAt = new Date();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
