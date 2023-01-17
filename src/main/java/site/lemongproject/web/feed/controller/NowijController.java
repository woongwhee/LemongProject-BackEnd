package site.lemongproject.web.feed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NowijController {

    @GetMapping("/nowij")
    public String Nowij() {
        return "Spring Boot and React 연동 테스트 \n";
    }
}
