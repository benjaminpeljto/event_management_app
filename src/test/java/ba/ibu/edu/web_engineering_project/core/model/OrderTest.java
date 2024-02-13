package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.OrderBoughtTicket;
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
    public void setUp() {
        order = new Order();
        order.setId("orderId123");
        order.setBuyerId("buyerId456");

        OrderBoughtTicket ticket1 = new OrderBoughtTicket();
        ticket1.setId("ticketId1");
        ticket1.setPrice(25.0);

        OrderBoughtTicket ticket2 = new OrderBoughtTicket();
        ticket2.setId("ticketId2");
        ticket2.setPrice(30.0);

        List<OrderBoughtTicket> boughtTickets = Arrays.asList(ticket1, ticket2);

        order.setBoughtTickets(boughtTickets);
        order.setTotalPrice(80.0);
        order.setPaymentType(PaymentType.CREDIT_CARD);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("orderId123", order.getId());
        assertEquals("buyerId456", order.getBuyerId());
        assertNotNull(order.getBoughtTickets());
        assertEquals(2, order.getBoughtTickets().size());
        assertEquals(80.0, order.getTotalPrice());
        assertEquals(PaymentType.CREDIT_CARD, order.getPaymentType());
    }
}
