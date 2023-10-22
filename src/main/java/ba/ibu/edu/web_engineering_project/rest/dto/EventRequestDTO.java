package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import java.time.LocalDateTime;
import java.util.Date;

public class EventRequestDTO {
    private String name;
    private String description;
    private String location;
    private EventCategory eventCategory;
    private String organizerId;
    private String organizerName;
    private String organizerEmail;
    private int availableSeats;
    private LocalDateTime occuranceDateTime;
    private EventStatus eventStatus;


    public EventRequestDTO(){}

    public EventRequestDTO(Event event){
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.eventCategory = event.getEventCategory();
        this.organizerId = event.getOrganizer().getId();
        this.organizerName = event.getOrganizer().getName();
        this.organizerEmail = event.getOrganizer().getEmail();
        this.availableSeats = event.getAvailableSeats();
        this.occuranceDateTime = event.getOccuranceDateTime();
        this.eventStatus = event.getEventStatus();
    }

    public Event toEntity(){

        Organizer organizer = new Organizer();
        organizer.setId(this.organizerId);
        organizer.setName(this.organizerName);
        organizer.setEmail(this.organizerEmail);

        Event event = new Event();

        event.setName(this.name);
        event.setDescription(this.description);
        event.setLocation(this.location);
        event.setEventCategory(this.eventCategory);
        event.setOrganizer(organizer);
        event.setAvailableSeats(this.availableSeats);
        event.setOccuranceDateTime(this.occuranceDateTime);
        event.setEventStatus(this.eventStatus);
        event.setCreationDate(new Date());
        return event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocalDateTime getOccuranceDateTime() {
        return occuranceDateTime;
    }

    public void setOccuranceDateTime(LocalDateTime occuranceDateTime) {
        this.occuranceDateTime = occuranceDateTime;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
