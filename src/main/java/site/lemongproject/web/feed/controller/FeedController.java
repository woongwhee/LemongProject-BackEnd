package site.lemongproject.web.feed.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import site.lemongproject.web.feed.domain.service.FeedService;
import site.lemongproject.web.feed.domain.vo.Feed;

import java.util.ArrayList;

@RequestMapping("/feed")
@Controller
public class FeedController {


    private FeedService feedService;

    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }



    @RequestMapping("feed/main")
    public String feedMain(Model model){
        ArrayList<Feed> list = feedService.selectFeed();
        model.addAttribute("list",list);
        return "feed/feedListView";
    }




}
