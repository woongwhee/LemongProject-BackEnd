package site.lemongproject.web.feed.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.feed.domain.service.FeedService;
import site.lemongproject.web.feed.domain.vo.Feed;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Map<String, Object> feedInsert(@RequestBody Map<String, Object> paramMap)throws SQLException, Exception {
        System.out.println(paramMap);

        int check = feedService.insertFeed(paramMap);

        Map<String, Object> result = new HashMap<>();
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;

    }



}
