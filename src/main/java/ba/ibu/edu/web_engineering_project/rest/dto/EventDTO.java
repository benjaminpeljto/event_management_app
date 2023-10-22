package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class EventDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private EventCategory eventCategory;
    private Organizer organizer;
    private int availableSeats;
    private LocalDateTime occuranceDateTime;
    private EventStatus eventStatus;
    private Date creationDate;


    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.eventCategory = event.getEventCategory();
        this.organizer = event.getOrganizer();
        this.availableSeats = event.getAvailableSeats();
        this.occuranceDateTime = event.getOccuranceDateTime();
        this.eventStatus = event.getEventStatus();
        this.creationDate = event.getCreationDate();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
