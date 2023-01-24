package site.lemongproject.web.feed.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.web.feed.model.service.FeedService;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
@Controller
public class FeedController {

    private FeedService feedService;

    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }
//
    @RequestMapping("feed/main")
    public String feedMain(Model model){
        ArrayList<Feed> list = feedService.selectFeed();
        model.addAttribute("list",list);
        return "feed/feedListView";
    }

    @GetMapping("/selectMyFeed")
    public List<Feed> selectMyFeed(){

        List<Feed> fList = feedService.selectMyFeed();

        return fList;
    }




}
