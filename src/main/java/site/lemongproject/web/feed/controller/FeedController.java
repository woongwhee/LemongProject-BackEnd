package site.lemongproject.web.feed.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.feed.model.service.FeedService;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.reply.model.vo.Reply;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/feed")
@RestController
@RequiredArgsConstructor
public class FeedController {
    final private FeedService feedService;

    // feed 전체 불러오기
    @RequestMapping("/main")
    public ResponseBody<List<FeedList>> feedSelect(){

        List<FeedList> list = feedService.selectFeed();
//        System.out.println(list);
        return ResponseBuilder.success(list);
    }

    // 피드 사진 넣기
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Map<String, Object> feedInsert(@RequestBody FeedInsert paramMap){
        System.out.println(paramMap); //FeedInsert(userNo=3, feedContent=마지막테스트, photoNo=[98, 99], feedNo=0)
        int check = feedService.insertFeed(paramMap);

        Map<String, Object> result = new HashMap<>();

        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }

    // 피드 수정
    @RequestMapping(value = "/updateFeed", method = RequestMethod.POST)
    public Map<String, Object> feedUpdate(@RequestBody FeedInsert updatefeed ){
        System.out.println(updatefeed);
        int check = feedService.updateFeed(updatefeed);
        Map<String, Object> result = new HashMap<>();
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }
    // 사진 수정하기
    @PostMapping("modifyFeedPhoto")
    public Map<String,Object> modifyPhoto(@RequestBody Map<String,Object> photoNo){
        System.out.println(photoNo);
        int check = feedService.modifyPhoto(photoNo);
        System.out.println("check : " + check);
        Map<String ,Object> result = new HashMap<>();
        if(check>0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }
    // 피드 삭제
    @RequestMapping(value = "/deleteFeed", method = RequestMethod.POST)
    public Map<String,Object> feedDelete(@RequestBody Map<String,Object> deleteFeedNo){
        System.out.println(deleteFeedNo);

        int check = feedService.deleteFeed(deleteFeedNo);
        System.out.println(check);
        Map<String, Object> result = new HashMap<>();

        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }

    // 피드 댓글 달기
    @RequestMapping(value = "/insertReply", method = RequestMethod.POST)
    public Map<String, Object> insertReply(@RequestBody Map<String, Object> paramMap){
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
    
//  피드 댓글 삭제
    @RequestMapping("/deleteReply")
    public Map<String,Object> deleteReply(@RequestBody Map<String, Object> data){
        System.out.println(data);

        int check = feedService.deleteReply(data);
        System.out.println(check);

        Map<String,Object> result = new HashMap<>();
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }

    // 피드 댓글 불러오기
    @RequestMapping("/listReply")
    public ResponseBody<List<Reply>> listReply(@RequestParam int feedNo){

        List<Reply> list = feedService.listReply(feedNo);

        return ResponseBuilder.success(list);
    }

//    -- 좋아요 수
//    SELECT COUNT(*) FROM HEART WHERE REF_NO=3;
//
//-- 좋아요 누름
//    INSERT INTO HEART (USER_NO, REF_TYPE, REF_NO) VALUES (USER_NO,1,FEED_NO);
//
//-- 좋아요 취소
//    DELETE FROM HEART WHERE USER_NO = USER_NO AND REF_NO=FEED_NO;

    // 사진 넣기
    @RequestMapping(value = "/insertPhoto", method = RequestMethod.POST)
    public ResponseBody<Photo> insertPhoto(@RequestBody MultipartFile[] files){
        Photo p = new Photo();
        FileUtil fileUtil = new FileUtil();

        p.setUserNo(3);
        fileUtil.saveFile(files[0], p);

        int result = feedService.insertPhoto(p);
        if(result>0){
            return ResponseBuilder.success(p);
        }else {
            return ResponseBuilder.success(result);
        }
    }
//     사진 지우기
//    @RequestMapping(value = "/deleteFeedPhoto", method = RequestMethod.POST)
    @GetMapping("/deleteFeedPhoto")
    public Map<String,Object> deletePhoto(@RequestParam int photoNo){
        System.out.println(photoNo);
        int check = feedService.deletePhoto(photoNo);
        Map<String,Object> result = new HashMap<>();
        System.out.println("check : "+check);
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }


}
