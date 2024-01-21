package ba.ibu.edu.web_engineering_project.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QRConfiguration {

    @Value("${qr.temp-directory}")
    private String pathToTemp;

    @Bean
    public String pathToTempQR(){ return this.pathToTemp; }
}
