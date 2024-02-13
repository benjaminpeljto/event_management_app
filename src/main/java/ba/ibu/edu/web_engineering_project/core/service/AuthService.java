package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.api.impl.mailsender.AsyncMailSender;
import ba.ibu.edu.web_engineering_project.api.impl.mailsender.GmailSMTPSender;
import ba.ibu.edu.web_engineering_project.api.lambda.ConfirmationMailLambda;
import ba.ibu.edu.web_engineering_project.core.api.mailsender.MailSender;
import ba.ibu.edu.web_engineering_project.core.exceptions.account.AccountDisabledException;
import ba.ibu.edu.web_engineering_project.core.exceptions.account.CustomBadCredentialsException;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.EmailAlreadyExistsException;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ValidationTokenExpiredException;
import ba.ibu.edu.web_engineering_project.core.model.User;
import ba.ibu.edu.web_engineering_project.core.model.enums.UserType;
import ba.ibu.edu.web_engineering_project.core.repository.UserRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.LoginDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.LoginRequestDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.UserDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final ConfirmationMailLambda mailSender;

    public AuthService(UserRepository userRepository, ConfirmationMailLambda mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        if(userRepository.existsByUsername(userRequestDTO.getUsername())){
            throw new EmailAlreadyExistsException("Username already exists.");
        }
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        User user = userRequestDTO.toEntity();
        user.setVerificationToken(String.valueOf(UUID.randomUUID()));
        user.setTokenValidUntil(new Date(System.currentTimeMillis() + 1000 * 60 * 60)); //Valid for one hour
        user.setActive(false);
        user.setUserType(UserType.MEMBER);
        user = userRepository.save(user);
        /*String mailgunResponse = mailgunSender.send(user, "Your account was successfully created. Please " +
                "confirm your email by clicking on the link below\n" +
                "localhost:8000/api/auth/confirm?verification=" + user.getVerificationToken(), "Confirm your account - Eventify");
        System.out.println(mailgunResponse);*/
        mailSender.sendConfirmationMail(user.getFirstName(), user.getEmail(), user.getVerificationToken());
        return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userRepository.findFirstByEmail(loginRequestDTO.getEmail());

        if (userOptional.isEmpty()) {
            userOptional = userRepository.findFirstByUsername(loginRequestDTO.getEmail());
        }

        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));

        if(!user.isActive()){
            throw new AccountDisabledException("Account not enabled, please confirm your account.");
        }

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
            );
        } catch (BadCredentialsException bce){
            throw new CustomBadCredentialsException("Bad credentials.");
        } catch (Exception e){
            throw new CustomBadCredentialsException("Login failed.");
        }

        String jwt = jwtService.generateToken(user, user.getId());
        return new LoginDTO(jwt, user.getUserType());
    }

    public String validateToken(String token){
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token does not exist."));
        if(user.isActive()){
            return "Account already activated.";
        }

        if(user.getTokenValidUntil().after(new Date(System.currentTimeMillis()))){
            user.setActive(true);
            userRepository.save(user);
            return "Account activated. Please sign in.";
        }
        else {
            userRepository.delete(user);
            throw new ValidationTokenExpiredException("Validation token has expired, please sign up again.");
        }

    }

}
