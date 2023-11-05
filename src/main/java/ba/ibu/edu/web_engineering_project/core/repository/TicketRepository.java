package ba.ibu.edu.web_engineering_project.core.repository;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
