package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.User;
import ba.ibu.edu.web_engineering_project.core.repository.UserRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.UserDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @Test
    public void getUsers_shouldReturnListOfUserDTOs() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(new User()));

        List<UserDTO> userDTOList = userService.getUsers();

        assertFalse(userDTOList.isEmpty());
        assertEquals(1, userDTOList.size());
    }

    @Test
    public void getUserById_shouldReturnUserDTOWhenUserExists() {
        User user = new User();
        Mockito.when(userRepository.findById("jhabsda")).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById("jhabsda");

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
    }

    @Test
    public void getUserById_shouldThrowExceptionWhenUserDoesNotExist() {
        Mockito.when(userRepository.findById("kjasndkjasnd")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> userService.getUserById("kjasndkjasnd"));
        assertEquals("The user with the given ID does not exist.", exception.getMessage());
    }

    @Test
    public void addUser_shouldSaveUserAndReturnUserDTO() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        User user = userRequestDTO.toEntity();
        user.setId("jdsbfsdbfjh");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.addUser(userRequestDTO);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
    }
}