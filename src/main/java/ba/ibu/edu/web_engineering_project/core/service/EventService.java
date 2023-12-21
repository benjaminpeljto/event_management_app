package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.event.InvalidEventStatusException;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.User;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import ba.ibu.edu.web_engineering_project.core.repository.EventRepository;
import ba.ibu.edu.web_engineering_project.core.repository.UserRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.EventDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.EventRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
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

    public List<EventDTO> getPendingEvents(){
        List<Event> pendingEvents =  eventRepository.findByEventStatus(EventStatus.PENDING_APPROVAL);
        return pendingEvents
                .stream()
                .map(EventDTO::new)
                .collect(toList());
    }

    public EventDTO addEvent(EventRequestDTO payload){
        Optional<User> organizer = userRepository.findById(payload.getOrganizerId());

        if(organizer.isEmpty()){
            throw new ResourceNotFoundException("Invalid access.");
        }

        Organizer organizerEmbedded = new Organizer();
        organizerEmbedded.setName(organizer.get().getUsername());
        organizerEmbedded.setEmail(organizer.get().getEmail());
        organizerEmbedded.setId(organizer.get().getId());
        Event event = payload.toEntity();
        event.setOrganizer(organizerEmbedded);
        event = eventRepository.save(event);
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

    public void orderUpdateEvent(Event event){
        eventRepository.save(event);
    }

    public EventDTO approveEvent(String id){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new ResourceNotFoundException("The event with the provided ID does not exist.");
        }
        else if (event.get().getEventStatus() == EventStatus.PENDING_APPROVAL){
            event.get().setEventStatus(EventStatus.SCHEDULED);
            return new EventDTO(eventRepository.save(event.get()));
        }
        else {
            throw new InvalidEventStatusException("Only unapproved events can be approved.");
        }
    }

    public EventDTO onGoEvent(String id){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new ResourceNotFoundException("The event with the provided ID does not exist.");
        }
        else if (event.get().getEventStatus() == EventStatus.SCHEDULED){
            event.get().setEventStatus(EventStatus.ONGOING);
            return new EventDTO(eventRepository.save(event.get()));
        }
        else {
            throw new InvalidEventStatusException("Only scheduled events can be set to ongoing.");
        }
    }

    public EventDTO cancelEvent(String id){
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new ResourceNotFoundException("The event with the provided ID does not exist.");
        }
        else if (event.get().getEventStatus() == EventStatus.ONGOING){
            event.get().setEventStatus(EventStatus.CANCELLED);
            return new EventDTO(eventRepository.save(event.get()));
        }
        else {
            throw new InvalidEventStatusException("Only ongoing events can be set to ongoing.");
        }
    }

    public void deleteEvent(String id){
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(eventRepository::delete);
    }


}
