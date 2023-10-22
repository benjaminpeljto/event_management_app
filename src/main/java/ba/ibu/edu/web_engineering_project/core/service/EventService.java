package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.repository.EventRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.EventDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.EventRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getEvents(){
        List<Event> events = eventRepository.findAll();
        return events
                .stream()
                .map(EventDTO::new)
                .collect(toList());
    }

    public EventDTO getEventById(String id){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new ResourceNotFoundException("The event with the provided ID does not exist.");
        }
        return new EventDTO(event.get());
    }

    public EventDTO addEvent(EventRequestDTO payload){
        Event event = eventRepository.save(payload.toEntity());
        return new EventDTO(event);
    }

    public EventDTO updateEvent(String id, EventRequestDTO payload){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new ResourceNotFoundException("The event with the provided ID does not exist.");
        }
        Event updatedEvent = payload.toEntity();
        updatedEvent.setId(event.get().getId());
        updatedEvent = eventRepository.save(updatedEvent);
        return new EventDTO(updatedEvent);
    }

    public void deleteEvent(String id){
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(eventRepository::delete);
    }
}
