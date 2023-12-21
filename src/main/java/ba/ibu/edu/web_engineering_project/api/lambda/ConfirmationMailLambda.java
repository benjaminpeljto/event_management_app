package ba.ibu.edu.web_engineering_project.api.lambda;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConfirmationMailLambda {
    private final RestTemplate restTemplate;
    private final String getAwsEmailConfirmationLambda;

    public ConfirmationMailLambda(RestTemplate restTemplate, String getAwsEmailConfirmationLambda) {
        this.restTemplate = restTemplate;
        this.getAwsEmailConfirmationLambda = getAwsEmailConfirmationLambda;
    }

    @Async
    public void sendConfirmationMail(String firstName, String email, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = String.format("{\"firstName\":\"%s\",\"email\":\"%s\",\"token\":\"%s\"}", firstName, email, token);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForEntity(getAwsEmailConfirmationLambda, request, String.class).getBody();

    }

}
