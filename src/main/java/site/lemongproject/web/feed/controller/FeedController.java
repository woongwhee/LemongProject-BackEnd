package site.lemongproject.web.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.lemongproject.web.feed.domain.service.FeedService;

@RequestMapping("/feed")
@Controller
public class FeedController {

    private FeedService feedService;

    public FeedController(FeedService feedService){
        this.feedService = feedService;

    }
}