package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.enums.PaymentType;

import java.util.List;

public class Order {
    private String id;
    private Buyer buyer;
    private List<String> boughtTicketIds;
    private double totalPrice;
    private PaymentType paymentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<String> getBoughtTicketIds() {
        return boughtTicketIds;
    }

    public void setBoughtTicketIds(List<String> boughtTicketId) {
        this.boughtTicketIds = boughtTicketId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
