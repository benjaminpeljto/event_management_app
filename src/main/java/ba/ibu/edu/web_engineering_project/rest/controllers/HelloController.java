package ba.ibu.edu.web_engineering_project.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/test")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value="name", defaultValue="World") String name){
        return "Heo " + name;
    }
}
