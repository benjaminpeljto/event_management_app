package ba.ibu.edu.web_engineering_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WebEngineeringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebEngineeringProjectApplication.class, args);
    }

}
