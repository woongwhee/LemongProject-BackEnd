package site.lemongproject.web.feed.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.feed.domain.service.FeedService;
import site.lemongproject.web.feed.domain.vo.Feed;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/feed")
@RestController
public class FeedController {


    private FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    // feed 전체 불러오기
    @RequestMapping("/main")
    public ResponseBody<List<Feed>> feedSelect(){

        List<Feed> list = feedService.selectFeed();

        return ResponseBuilder.success(list);
    }

    // 피드 넣기
    @GetMapping("/insert")
    public ResponseBody<Feed> feedInsert(@RequestBody Feed f){

        int result = feedService.insertFeed(f);

        if(result>0){
            return ResponseBuilder.success(result);
        }
    }



}
