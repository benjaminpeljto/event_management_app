package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.repository.TicketRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.TicketRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@AutoConfigureMockMvc
@SpringBootTest
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void testGetTickets() {
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket()));

        List<Ticket> tickets = ticketService.getTickets();

        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testGetTicketById() {
        String ticketId = "ticketId";
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketById(ticketId);

        assertNotNull(result);
        assertEquals(ticketId, result.getId());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testGetTicketById_WhenTicketNotFound() {
        String ticketId = "nonExistentTicketId";
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ticketService.getTicketById(ticketId));
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testAddTicket() {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        Ticket ticket = ticketRequestDTO.toEntity();
        ticket.setId("ticketId");
        when(ticketRepository.save(any())).thenReturn(ticket);

        Ticket result = ticketService.addTicket(ticketRequestDTO);

        assertNotNull(result);
        assertEquals(ticket.getId(), result.getId());
        verify(ticketRepository, times(1)).save(any());
    }
}
