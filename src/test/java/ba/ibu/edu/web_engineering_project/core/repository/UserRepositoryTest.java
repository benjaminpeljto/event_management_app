package ba.ibu.edu.web_engineering_project.core.repository;

import ba.ibu.edu.web_engineering_project.core.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    private void cleanUp() {
        userRepository.deleteById("indentificationBroj");
    }

    @Test
    public void testFindFirstByEmailLike() {
        User user = createUser();
        user.setEmail("benjamin@gmail.com");
        userRepository.save(user);

        Optional<User> result = userRepository.findFirstByEmailLike("benjamin@");

        assertTrue(result.isPresent());
        assertEquals("benjamin@gmail.com", result.get().getEmail());
    }

    @Test
    public void testFindByVerificationToken() {
        User user = createUser();
        user.setVerificationToken("token123");
        userRepository.save(user);

        Optional<User> result = userRepository.findByVerificationToken("token123");

        assertTrue(result.isPresent());
        assertEquals("token123", result.get().getVerificationToken());
    }

    @Test
    public void testFindFirstByEmail() {
        User user = createUser();
        user.setEmail("benjamin@gmail.com");
        userRepository.save(user);

        Optional<User> result = userRepository.findFirstByEmail("benjamin@gmail.com");

        assertTrue(result.isPresent());
        assertEquals("benjamin@gmail.com", result.get().getEmail());
    }

    @Test
    public void testFindFirstByUsername() {
        User user = createUser();
        userRepository.save(user);

        Optional<User> result = userRepository.findFirstByUsername("userName");

        assertTrue(result.isPresent());
        assertEquals("userName", result.get().getUsername());
    }

    @Test
    public void testExistsByEmail() {
        User user = createUser();
        user.setEmail("benjamin@gmail.com");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("benjamin@gmail.com");

        assertTrue(exists);
    }

    @Test
    public void testLastAddedUser() {
        User user = createUser();
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertEquals("userName", users.get(users.size()-1).getUsername());
    }

    private User createUser() {
        User user = new User();
        user.setId("indentificationBroj");
        user.setUsername("userName");
        return user;
    }

}