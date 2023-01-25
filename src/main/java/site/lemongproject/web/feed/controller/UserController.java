package site.lemongproject.web.feed.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/board/insert")
    public String boardinsert(@RequestParam String id, @RequestParam String content,
    @RequestParam String picture){
        System.out.println(id);
        System.out.println(content);
        System.out.println(picture);
        return "gd";
    }
}
