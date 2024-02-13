package ba.ibu.edu.web_engineering_project.core.repository;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventCategory;
import ba.ibu.edu.web_engineering_project.core.model.enums.EventStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByEventStatus(EventStatus eventStatus);
    List<Event> findByEventStatusAndEventCategory(EventStatus eventStatus, EventCategory eventCategory);
    List<Event> findByEventStatusAndNameIgnoreCaseLikeOrDescriptionIgnoreCaseLikeOrLocationIgnoreCaseLike(
            EventStatus eventStatus,
            String name,
            String description,
            String location
    );
}
