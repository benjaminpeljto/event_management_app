package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.embedded.SeatsPerTicketType;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRequestDTO {
    private String name;
    private String description;
    private String location;
    private EventCategory eventCategory;
    private String organizerId;
    private LocalDateTime occuranceDateTime;
    private EventStatus eventStatus;
    private List<EventRequestTicketTypeDTO> ticketTypes;


    public EventRequestDTO(){}

    public EventRequestDTO(Event event){
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.eventCategory = event.getEventCategory();
        this.organizerId = event.getOrganizer().getId();
        this.occuranceDateTime = event.getOccuranceDateTime();
        this.eventStatus = event.getEventStatus();
    }



    public Event toEntity(){

        Event event = new Event();

        event.setName(this.name);
        event.setDescription(this.description);
        event.setLocation(this.location);
        event.setEventCategory(this.eventCategory);
        event.setOccuranceDateTime(this.occuranceDateTime);
        event.setEventStatus(this.eventStatus);
        event.setCreationDate(new Date());

        // Mapping TicketPurchaseTypeQuantityDTO into embedded SeatsPerTicketType type
        List<SeatsPerTicketType> seatsPerTicketTypes = new ArrayList<>();
        if (this.ticketTypes != null) {
            for (EventRequestTicketTypeDTO ticketTypeDTO : this.ticketTypes) {
                SeatsPerTicketType seatsPerTicketType = new SeatsPerTicketType();
                seatsPerTicketType.setTicketType(ticketTypeDTO.getTicketType());
                seatsPerTicketType.setQuantity(ticketTypeDTO.getQuantity());
                seatsPerTicketType.setTypePrice(ticketTypeDTO.getTypePrice());
                seatsPerTicketTypes.add(seatsPerTicketType);
            }
        }
        event.setSeatsPerTicketType(seatsPerTicketTypes);
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

    public List<EventRequestTicketTypeDTO> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<EventRequestTicketTypeDTO> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }
}
