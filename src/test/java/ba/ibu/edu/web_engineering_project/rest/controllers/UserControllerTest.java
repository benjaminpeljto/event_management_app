package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.service.UserService;
import ba.ibu.edu.web_engineering_project.rest.controllers.UserController;
import ba.ibu.edu.web_engineering_project.rest.dto.UserDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Mocking
        List<UserDTO> userDTOList = List.of(new UserDTO(), new UserDTO());
        when(userService.getUsers()).thenReturn(userDTOList);

        // Test
        ResponseEntity<List<UserDTO>> responseEntity = userController.getUsers();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTOList, responseEntity.getBody());
        verify(userService, times(1)).getUsers();
    }

    @Test
    void testGetUserById() {
        // Mocking
        String userId = "userId";
        UserDTO userDTO = new UserDTO();
        when(userService.getUserById(userId)).thenReturn(userDTO);

        // Test
        ResponseEntity<UserDTO> responseEntity = userController.getUserById(userId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void testRegister() {
        // Mocking
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        UserDTO userDTO = new UserDTO();
        when(userService.addUser(userRequestDTO)).thenReturn(userDTO);

        // Test
        ResponseEntity<UserDTO> responseEntity = userController.register(userRequestDTO);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
        verify(userService, times(1)).addUser(userRequestDTO);
    }

    @Test
    void testUpdateUser() {
        // Mocking
        String userId = "userId";
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        UserDTO userDTO = new UserDTO();
        when(userService.updateUser(userId, userRequestDTO)).thenReturn(userDTO);

        // Test
        ResponseEntity<UserDTO> responseEntity = userController.updateUser(userId, userRequestDTO);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
        verify(userService, times(1)).updateUser(userId, userRequestDTO);
    }

    @Test
    void testDeleteUser() {
        // Mocking
        String userId = "userId";

        // Test
        ResponseEntity<Void> responseEntity = userController.deleteUser(userId);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void testFilterUser() {
        // Mocking
        String email = "test@example.com";
        UserDTO userDTO = new UserDTO();
        when(userService.filterByEmail(email)).thenReturn(userDTO);

        // Test
        ResponseEntity<UserDTO> responseEntity = userController.filterUser(email);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
        verify(userService, times(1)).filterByEmail(email);
    }
}
