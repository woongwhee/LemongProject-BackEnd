package site.lemongproject.web.feed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller : 주로 View에 반환하기 위해
// @ResponseBody : Data를 반환해야 하는 경우
@RestController // 원래 Controller에서 viewresult 로 넘겨주는거밖에안함. 이친구는 값을 사용자에게 그냥 돌려준다
                // 주용도는 Json 형태로 객체 데이터를 반
public class NowijController {

    @GetMapping("/home")
    public String getHome(){
        return "Hello World!"; // Data
    }
}
