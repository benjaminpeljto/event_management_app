package ba.ibu.edu.web_engineering_project.core.model;

import ba.ibu.edu.web_engineering_project.core.model.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    private User user;

    @BeforeEach
    public void setUpWithSetters() {
        user = new User();
        user.setId("ousdhfisndfsdf");
        user.setUsername("testUser");
        user.setFirstName("Beno");
        user.setLastName("Benou");
        user.setAddress("Alipašina 167");
        user.setEmail("benjo@gmail.com");
        user.setPassword("passriječ");
        user.setUserType(UserType.STAFF);
        user.setVerificationToken("token123");
        user.setTokenValidUntil(new Date());
        user.setActive(false);
        user.setCreationDate(new Date());
    }

    @Test
    public void testGetters() {
        assertEquals("ousdhfisndfsdf", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("Beno", user.getFirstName());
        assertEquals("Benou", user.getLastName());
        assertEquals("Alipašina 167", user.getAddress());
        assertEquals("benjo@gmail.com", user.getEmail());
        assertEquals("passriječ", user.getPassword());
        assertEquals(UserType.STAFF, user.getUserType());
        assertEquals("token123", user.getVerificationToken());
        assertEquals(false, user.isActive());
    }

    @Test
    public void testUserDetailsMethods() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertFalse(user.isEnabled());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("STAFF")));
    }
}