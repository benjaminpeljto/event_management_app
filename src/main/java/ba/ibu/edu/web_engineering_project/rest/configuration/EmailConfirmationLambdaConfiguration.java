package ba.ibu.edu.web_engineering_project.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfirmationLambdaConfiguration {

    @Value("${aws.lambda.email-confirmation-lambda}")
    private String aws_email_confirmation_lambda;

    @Bean
    public String getAwsEmailConfirmationLambda() {
        return this.aws_email_confirmation_lambda;
    }
}
