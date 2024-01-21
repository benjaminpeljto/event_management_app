package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;

public class EventRequestTicketTypeDTO {
    private TicketType ticketType;
    private int quantity;
    private double typePrice;

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(double typePrice) {
        this.typePrice = typePrice;
    }
}
