package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTest {
    private Order order;

    @BeforeEach
    public void setUpWithSetters() {
        this.order = new Order();
        order.setId("dksnfsdjs");
        Buyer buyer = new Buyer("Benjamin Peljto", "beno@email.com");
        order.setBuyer(buyer);
        List<String> boughtTicketIds = Arrays.asList("ticket1", "ticket2");
        order.setBoughtTicketIds(boughtTicketIds);
        order.setTotalPrice(100.0);
        order.setPaymentType(PaymentType.CREDIT_CARD);
    }
    @Test
    public void testGetters() {
        assertEquals("dksnfsdjs", order.getId());
        assertNotNull(order.getBuyer());
        assertEquals("Benjamin Peljto", order.getBuyer().getName());
        assertEquals("beno@email.com", order.getBuyer().getEmail());
        assertTrue(order.getBoughtTicketIds().contains("ticket1"));
        assertTrue(order.getBoughtTicketIds().contains("ticket2"));
        assertEquals(100.0, order.getTotalPrice());
        assertEquals(PaymentType.CREDIT_CARD, order.getPaymentType());
    }
}