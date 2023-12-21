package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.SeatsPerTicketType;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
public class Event {
    @Id
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
    private List<SeatsPerTicketType> seatsPerTicketType;


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

    public List<SeatsPerTicketType> getSeatsPerTicketType() {
        return seatsPerTicketType;
    }

    public void setSeatsPerTicketType(List<SeatsPerTicketType> seatsPerTicketType) {
        this.seatsPerTicketType = seatsPerTicketType;
    }
}
