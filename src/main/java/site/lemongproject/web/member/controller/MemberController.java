package site.lemongproject.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.common.scheduler.SchedulerService;

@RestController
public class MemberController {
    @Autowired SchedulerService schedulerService;
    @RequestMapping("/api/member")
    public String hi(){
        schedulerService.updateHoliday();
        return "hi";
    }
}
