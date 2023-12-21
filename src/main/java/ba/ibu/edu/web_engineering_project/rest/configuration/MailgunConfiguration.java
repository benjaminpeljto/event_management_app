package ba.ibu.edu.web_engineering_project.rest.configuration;

import ba.ibu.edu.web_engineering_project.api.impl.mailsender.MailgunSender;
import ba.ibu.edu.web_engineering_project.core.api.mailsender.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class MailgunConfiguration {
    @Value("${email.mailgun.from-email}")
    private String fromEmail;

    @Value("${email.mailgun.username}")
    private String username;

    @Value("${email.mailgun.password}")
    private String password;


    @Value("${email.mailgun.domain}")
    private String domain;

    @Bean
    public String fromEmail(){
        return this.fromEmail;
    }

    @Bean
    public MailSender mailgunMailSender(RestTemplate restTemplate, String fromEmail){
        return new MailgunSender(restTemplate, fromEmail);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.uriTemplateHandler(new DefaultUriBuilderFactory(domain)).basicAuthentication(username, password).build();
    }
}
