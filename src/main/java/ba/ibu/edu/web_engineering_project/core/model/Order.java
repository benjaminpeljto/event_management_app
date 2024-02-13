package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.OrderBoughtTicket;
import ba.ibu.edu.web_engineering_project.core.model.enums.PaymentType;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Order {
    @Id
    private String id;
    private String buyerId;
    private List<OrderBoughtTicket> orderBoughtTickets;
    private double totalPrice;
    private PaymentType paymentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<OrderBoughtTicket> getBoughtTickets() {
        return orderBoughtTickets;
    }

    public void setBoughtTickets(List<OrderBoughtTicket> orderBoughtTickets) {
        this.orderBoughtTickets = orderBoughtTickets;
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
