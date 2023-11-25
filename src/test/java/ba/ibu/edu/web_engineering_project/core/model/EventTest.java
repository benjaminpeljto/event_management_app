package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.embedded.Organizer;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventTest {
    private static final String DUMMY_ID = "DummyId";
    private static final String DUMMY_NAME = "DummyName";
    private static final String DUMMY_DESCRIPTION = "DummyDescription";
    private static final String DUMMY_LOCATION = "DummyLocation";
    private static final EventCategory DUMMY_CATEGORY = EventCategory.GIG;
    private static final Organizer DUMMY_ORGANIZER = new Organizer("Benjo");
    private static final int DUMMY_AVAILABLE_SEATS = 100;
    private static final LocalDateTime DUMMY_OCCURANCE_DATE_TIME = LocalDateTime.now();
    private static final EventStatus DUMMY_STATUS = EventStatus.CANCELLED;
    private static final Date DUMMY_CREATION_DATE = new Date();
    private Event event;

    @BeforeEach
    public void setUp() {
        this.event = new Event();
        event.setId(DUMMY_ID);
        event.setName(DUMMY_NAME);
        event.setDescription(DUMMY_DESCRIPTION);
        event.setLocation(DUMMY_LOCATION);
        event.setEventCategory(DUMMY_CATEGORY);
        event.setOrganizer(DUMMY_ORGANIZER);
        event.setAvailableSeats(DUMMY_AVAILABLE_SEATS);
        event.setOccuranceDateTime(DUMMY_OCCURANCE_DATE_TIME);
        event.setEventStatus(DUMMY_STATUS);
        event.setCreationDate(DUMMY_CREATION_DATE);
    }

    @Test
    public void testGetters() {
        assertEquals(DUMMY_ID, event.getId());
        assertEquals(DUMMY_NAME, event.getName());
        assertEquals(DUMMY_DESCRIPTION, event.getDescription());
        assertEquals(DUMMY_LOCATION, event.getLocation());
        assertEquals(DUMMY_CATEGORY, event.getEventCategory());
        assertEquals(DUMMY_ORGANIZER, event.getOrganizer());
        assertEquals(DUMMY_AVAILABLE_SEATS, event.getAvailableSeats());
        assertEquals(DUMMY_OCCURANCE_DATE_TIME, event.getOccuranceDateTime());
        assertEquals(DUMMY_STATUS, event.getEventStatus());
        assertEquals(DUMMY_CREATION_DATE, event.getCreationDate());
    }

    @Test
    public void testSetters() {
        Event newEvent = new Event();

        newEvent.setId("NewId");
        newEvent.setName("NewName");
        newEvent.setDescription("NewDescription");
        newEvent.setLocation("NewLocation");
        newEvent.setEventCategory(EventCategory.FOOD);
        newEvent.setOrganizer(new Organizer("NewOrganizer"));
        newEvent.setAvailableSeats(200);
        LocalDateTime newDateTime = LocalDateTime.now().plusDays(1);
        newEvent.setOccuranceDateTime(newDateTime);
        newEvent.setEventStatus(EventStatus.SOLD_OUT);
        newEvent.setCreationDate(new Date());

        assertEquals("NewId", newEvent.getId());
        assertEquals("NewName", newEvent.getName());
        assertEquals("NewDescription", newEvent.getDescription());
        assertEquals("NewLocation", newEvent.getLocation());
        assertEquals(EventCategory.FOOD, newEvent.getEventCategory());
        assertEquals(new Organizer("NewOrganizer").getName(), newEvent.getOrganizer().getName());
        assertEquals(200, newEvent.getAvailableSeats());
        assertEquals(newDateTime, newEvent.getOccuranceDateTime());
        assertEquals(EventStatus.SOLD_OUT, newEvent.getEventStatus());
        assertNotNull(newEvent.getCreationDate());
    }
}