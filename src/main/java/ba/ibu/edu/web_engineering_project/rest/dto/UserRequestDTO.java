package ba.ibu.edu.web_engineering_project.rest.dto;

import ba.ibu.edu.web_engineering_project.core.model.User;
import ba.ibu.edu.web_engineering_project.core.model.enums.UserType;

import java.util.Date;

public class UserRequestDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private UserType userType;

    public UserRequestDTO(){}

    public UserRequestDTO(User user){
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }

    public User toEntity(){
        User user = new User();
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setAddress(this.address);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setUserType(this.userType);
        user.setCreationDate(new Date());
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
