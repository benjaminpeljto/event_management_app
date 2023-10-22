package ba.ibu.edu.web_engineering_project.core.repository;

import ba.ibu.edu.web_engineering_project.core.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

}
