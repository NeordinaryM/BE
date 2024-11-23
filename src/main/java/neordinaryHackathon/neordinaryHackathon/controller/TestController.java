package neordinaryHackathon.neordinaryHackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TestController {

    @Operation(summary = "test 및 health 체크 용도")
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
