package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Buyer;
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
        ticket.setBuyer(new Buyer("Edin Dzeko", "edzeko@gmail.com"));
        ticket.setEventId("kabdkasbda");
        ticket.setCreatedAt(new Date());
        ticket.setExpiresAt(new Date(System.currentTimeMillis() + 3600000)); // Set expiresAt to one hour from now
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("jhbasjdbasjbha", ticket.getId());
        assertEquals(TicketType.REGULAR, ticket.getTicketType());
        assertEquals(50.0, ticket.getPrice());
        assertNotNull(ticket.getBuyer());
        assertEquals("Edin Dzeko", ticket.getBuyer().getName());
        assertEquals("edzeko@gmail.com", ticket.getBuyer().getEmail());
        assertEquals("kabdkasbda", ticket.getEventId());
        assertNotNull(ticket.getCreatedAt());
        assertNotNull(ticket.getExpiresAt());
    }

    @Test
    public void testTicketExpiration() {
        // Assuming the expiresAt is set to one hour from now in the setUp method
        assertTrue(ticket.getExpiresAt().after(new Date()));
    }
}