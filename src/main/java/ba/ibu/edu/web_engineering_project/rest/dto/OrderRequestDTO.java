package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.Order;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.enums.PaymentType;

import java.util.List;

public class OrderRequestDTO {
    private String buyerId;
    private String buyerName;
    private String buyerEmail;
    private List<String> boughtTicketIds;
    private double totalPrice;
    private PaymentType paymentType;


    public OrderRequestDTO(){}

    public OrderRequestDTO(Order order){
        this.buyerId = order.getBuyer().getId();
        this.buyerName = order.getBuyer().getName();
        this.buyerEmail = order.getBuyer().getEmail();
        this.boughtTicketIds = order.getBoughtTicketIds();
        this.totalPrice = order.getTotalPrice();
        this.paymentType = order.getPaymentType();
    }

    public Order toEntity(){
        Buyer buyer = new Buyer();
        buyer.setId(this.buyerId);
        buyer.setName(this.buyerName);
        buyer.setEmail(this.buyerEmail);

        Order order = new Order();
        order.setBuyer(buyer);
        order.setBoughtTicketIds(this.boughtTicketIds);
        order.setTotalPrice(this.totalPrice);
        order.setPaymentType(this.paymentType);
        return order;
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

    public List<String> getBoughtTicketId() {
        return boughtTicketIds;
    }

    public void setBoughtTicketId(List<String> boughtTicketId) {
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
