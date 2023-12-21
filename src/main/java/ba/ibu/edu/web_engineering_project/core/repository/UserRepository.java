package ba.ibu.edu.web_engineering_project.core.repository;

import ba.ibu.edu.web_engineering_project.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findFirstByEmailLike(String emailPattern);
    Optional<User> findByVerificationToken(String verificationToken);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByUsername(String username);
    boolean existsByEmail(String email);
}
