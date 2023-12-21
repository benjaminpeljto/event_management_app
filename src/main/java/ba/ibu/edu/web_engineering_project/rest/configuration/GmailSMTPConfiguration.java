package ba.ibu.edu.web_engineering_project.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GmailSMTPConfiguration {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Bean
    public String fromEmailGmail(){ return this.fromEmail; }
}
