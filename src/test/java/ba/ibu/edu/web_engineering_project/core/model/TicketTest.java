package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.TicketEvent;
import ba.ibu.edu.web_engineering_project.core.model.enums.TicketType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketTest {

    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket();
        ticket.setId("jhbasjdbasjbha");
        ticket.setTicketType(TicketType.REGULAR);
        ticket.setPrice(50.0);
        ticket.setBuyerId("buyer123");
        ticket.setCreatedAt(new Date());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("jhbasjdbasjbha", ticket.getId());
        assertEquals(TicketType.REGULAR, ticket.getTicketType());
        assertEquals(50.0, ticket.getPrice());
        assertEquals("buyer123", ticket.getBuyerId());
        assertNotNull(ticket.getCreatedAt());
    }
}
