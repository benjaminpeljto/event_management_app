package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.SeatsPerTicketType;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class EventDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private EventCategory eventCategory;
    private Organizer organizer;
    private LocalDateTime occuranceDateTime;
    private EventStatus eventStatus;
    private Date creationDate;
    private List<SeatsPerTicketType> seatsPerTicketType;
    private int totalAvailableSeats;
    private String image;


    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.eventCategory = event.getEventCategory();
        this.organizer = event.getOrganizer();
        this.occuranceDateTime = event.getOccuranceDateTime();
        this.eventStatus = event.getEventStatus();
        this.creationDate = event.getCreationDate();
        this.seatsPerTicketType = event.getSeatsPerTicketType();
        this.totalAvailableSeats = calculateTotalAvailableSeats(this.seatsPerTicketType);
        this.image = event.getImage();
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

    public int getTotalAvailableSeats() {
        return totalAvailableSeats;
    }

    public void setTotalAvailableSeats(int totalAvailableSeats) {
        this.totalAvailableSeats = totalAvailableSeats;
    }

    private int calculateTotalAvailableSeats(List<SeatsPerTicketType> seatsPerTicketType) {
        int totalSeats = 0;

        if (seatsPerTicketType != null) {
            for (SeatsPerTicketType seatType : seatsPerTicketType) {
                totalSeats += seatType.getQuantity();
            }
        }

        return totalSeats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
