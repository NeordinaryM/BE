package neordinaryHackathon.neordinaryHackathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String testController() {
        return "Hello World";
    }

}
