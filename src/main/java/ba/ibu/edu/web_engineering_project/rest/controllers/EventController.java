package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.service.EventService;
import ba.ibu.edu.web_engineering_project.rest.dto.EventDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.EventRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<EventDTO>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable String id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventRequestDTO event){
        return ResponseEntity.ok(eventService.addEvent(event));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable String id ,@RequestBody EventRequestDTO event){
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
