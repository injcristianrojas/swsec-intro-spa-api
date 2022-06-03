package cl.injcristianrojas.swsecintro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    String getTestInfo() {
        return "It works!";
    }

}
