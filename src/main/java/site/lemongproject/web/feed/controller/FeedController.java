package site.lemongproject.web.feed.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.feed.model.service.FeedService;
import site.lemongproject.web.feed.model.vo.Feed;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RequestMapping("/feed")
@RestController
@RequiredArgsConstructor
public class FeedController {


    final private FeedService feedService;


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

//    // 피드 사진 넣기
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public String feedUploadPicture(@RequestBody String formData){
//        if(formData != null){
//            System.out.println("컨트롤러까지옴");
//        }
////        System.out.println(formData);
//        return formData;
//    }

    // 피드 댓글 달기
    @RequestMapping(value = "/insertReply", method = RequestMethod.POST)
    public Map<String, Object> feedReply(@RequestBody Map<String, Object> paramMap){
        System.out.println(paramMap);
        int check = feedService.insertFeedReply(paramMap);

        Map<String, Object> result = new HashMap<>();

        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }

//    @RequestMapping("/replySelect")
//    public ResponseBody<List<Reply>>(@RequestBody int feedNo){
//
//        List<Reply> list = feedService.selectReply();
//
//        return ResponseBuilder.success(list);
//    }





}
