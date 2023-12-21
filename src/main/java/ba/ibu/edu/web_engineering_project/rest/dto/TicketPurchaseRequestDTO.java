package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.enums.PaymentType;

import java.util.List;

public class TicketPurchaseRequestDTO {

    private String userId;
    private String eventId;
    private List<TicketPurchaseTypeQuantityDTO> ticketTypes;
    private PaymentType paymentType;

    public String getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<TicketPurchaseTypeQuantityDTO> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketPurchaseTypeQuantityDTO> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
