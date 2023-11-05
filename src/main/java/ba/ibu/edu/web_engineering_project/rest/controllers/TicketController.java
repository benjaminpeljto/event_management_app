package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.service.TicketService;
import ba.ibu.edu.web_engineering_project.rest.dto.TicketRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<Ticket>> getTickets(){
        return ResponseEntity.ok(ticketService.getTickets());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<Ticket> addTicket(@RequestBody TicketRequestDTO payload){
        return ResponseEntity.ok(ticketService.addTicket(payload));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody TicketRequestDTO payload){
        return ResponseEntity.ok(ticketService.updateTicket(id, payload));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
