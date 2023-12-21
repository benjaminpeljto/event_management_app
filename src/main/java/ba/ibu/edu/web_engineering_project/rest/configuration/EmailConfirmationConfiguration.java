package ba.ibu.edu.web_engineering_project.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfirmationConfiguration {

    @Value("${auth.confirmation-link}")
    private String confirmationUrl;

    @Bean
    public String getEmailConfirmationUrl(){ return this.confirmationUrl; }
}
