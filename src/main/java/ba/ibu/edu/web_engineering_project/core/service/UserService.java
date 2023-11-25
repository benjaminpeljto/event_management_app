package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.api.impl.mailsender.MailgunSender;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.User;
import ba.ibu.edu.web_engineering_project.core.repository.UserRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.UserDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.UserRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MailgunSender mailgunSender;

    public UserService(UserRepository userRepository, MailgunSender mailgunSender) {
        this.userRepository = userRepository;
        this.mailgunSender = mailgunSender;
    }


    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    public UserDTO getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }

    public UserDTO addUser(UserRequestDTO payload){
        User user = payload.toEntity();
        user.setVerificationToken(String.valueOf(UUID.randomUUID()));
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    public UserDTO updateUser(String id, UserRequestDTO payload){
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public UserDTO filterByEmail(String email){
        Optional<User> user = userRepository.findFirstByEmailLike(email);
        return user
                .map(UserDTO::new)
                .orElse(null);
    }

    public String validateToken(String token){
        Optional<User> userOptional = userRepository.findByVerificationToken(token);

        if(userOptional.isPresent()){
            userOptional.get().setActive(true);
            userRepository.save(userOptional.get());
            return "Account successfully activated.";
        }
        else{
            return "Account with provided token does not exist.";
        }

    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findFirstByUsername(username)
                        .orElseGet(() -> {
                            return userRepository.findFirstByEmail(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                        });
            }
        };
    };
}
