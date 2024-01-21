package ba.ibu.edu.web_engineering_project.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaAWSConfiguration {

    @Value("${aws.lambda.email-confirmation-lambda}")
    private String aws_email_confirmation_lambda;

    @Value("${aws.lambda.generate-send-tickets-lambda}")
    private String aws_generate_send_tickets_lambda;

    @Bean
    public String getAwsEmailConfirmationLambda() {
        return this.aws_email_confirmation_lambda;
    }

    @Bean
    public String getAwsGenerateSendTicketsLambda() { return this.aws_generate_send_tickets_lambda; }
}
