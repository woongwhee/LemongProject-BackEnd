package site.lemongproject.web.feed.controller;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.feed.model.service.FeedService;
import site.lemongproject.web.feed.model.vo.ReplyAlarm;

import site.lemongproject.web.feed.model.vo.ReplyAlarmList;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.feed.model.vo.Reply;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/feed")
@RestController
@RequiredArgsConstructor
public class FeedController {
    final private FeedService feedService;

    @PostMapping("/loginFeedUserNo")
    public int loginFeedUserNo( @SessionAttribute("loginUser") Profile loginUser){
        return loginUser.getUserNo();
    }

    @PostMapping("/feedProfile")
    public Map<String, Object> userProfilePhoto(@RequestBody Map<String,Object> userNo){
        Map<String, Object> result = feedService.userProfile(userNo);
        return result;
    }

    // feed 전체 불러오기
    @RequestMapping("/main/{page}")
    public ResponseBody<List<FeedList>> feedSelect(@PathVariable(value = "page")int page){
        List<FeedList> list = feedService.selectFeed(page);
//        System.out.println(list);
        return ResponseBuilder.success(list);
    }
    @RequestMapping("/feedCount")
    public ResponseBody<Integer> feedCount(){
        int result = feedService.countFeed();
        return ResponseBuilder.success(result);
    }

    // 피드 사진 넣기PHOTO
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Map<String, Object> feedInsert(@RequestBody FeedInsert paramMap, @SessionAttribute("loginUser") Profile loginUser){
        paramMap.setUserNo(loginUser.getUserNo());
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
    public Map<String, Object> feedUpdate(@RequestBody FeedInsert updatefeed, @SessionAttribute("loginUser") Profile loginUser ){
//        System.out.println("updateFeed : " + updatefeed);
        updatefeed.setUserNo(loginUser.getUserNo());
//        System.out.println(updatefeed);
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
//        System.out.println(photoNo);
        int check = feedService.modifyPhoto(photoNo);
//        System.out.println("check : " + check);
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
//        System.out.println(deleteFeedNo);
        int check = feedService.deleteFeed(deleteFeedNo);
//        System.out.println(check);
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
    public Map<String, Object> insertReply(@RequestBody Map<String, Object> paramMap,  @SessionAttribute("loginUser") Profile loginUser){
//        System.out.println("reply insert"+paramMap)

        paramMap.put("loginUserNo",loginUser.getUserNo());
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
        int check = feedService.deleteReply(data);
//
        Map<String,Object> result = new HashMap<>();
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }
    // 피드 댓글 불러오기
    @GetMapping("/listReply")
    public ResponseBody<List<Reply>> listReply(@RequestParam int feedNo){
//        System.out.println("list" + feedNo);
        List <Reply> list = feedService.listReply(feedNo);
        return ResponseBuilder.success(list);
    }

    // 피드 댓글수 불러오기
    @GetMapping("countReply")
    public int countReply(@RequestParam int feedNo){
//        System.out.println(feedNo);
        int check = feedService.countReply(feedNo);
        return check;
    }

    // USER_NO에 해당하는 내가 작성한 피드정보 리스트 가져오기(마이페이지용)
    @GetMapping("/selectMyFeedList")
    public ResponseBody<List<FeedList>> selectMyFeedList(@RequestParam(value = "userNo" , required = false)int userNo){
//        System.out.println(userNo + "myfeed");
        FeedList f = new FeedList();
        f.setUserNo(userNo);

        List<FeedList> fList = feedService.selectMyFeedList(f);
//        System.out.println(fList);
        return ResponseBuilder.success(fList);
    }

    // FEED_NO에 해당하는 이미지 경로포함해서 다가져오기
    @GetMapping("/searchImg")
    public ResponseBody<List<FeedList>> searchImg(@RequestParam(value = "feedNo" , required = false)int feedNo){
        FeedList f = new FeedList();
        f.setFeedNo(feedNo);

        List<FeedList> sList = feedService.searchImg(f);

        return ResponseBuilder.success(sList);
    }


//    -- 좋아요 수
    @PostMapping("heartCount")
    public int heartCount(@RequestBody Map<String, Object> data){
        int check = feedService.heartCount(data);
        return check;
    }
//-- 좋아요 누름
    @PostMapping("/heartClick")
    public int heartClick(@RequestBody Map<String, Object> data){
//        System.out.println(data);
        int check = feedService.heartClick(data);
        return check;
    }
//-- 좋아요 취소
    @PostMapping("/heartCancel")
    public int heartCancel(@RequestBody Map<String, Object> data){
//        System.out.println(data);
        int check = feedService.heartCancel(data);
        return check;
    }
    // 좋아요 확인
    @PostMapping("/heartState")
    public int heartState(@RequestBody Map<String, Object> data){
//        System.out.println(data);
        int check = feedService.heartState(data);
        return check;
    }



    // 사진 넣기
    @RequestMapping(value = "/insertPhoto", method = RequestMethod.POST)
    public ResponseBody<Photo> insertPhoto(@RequestBody MultipartFile[] files, @SessionAttribute("loginUser") Profile loginUser ){
        Photo p = new Photo();
        FileUtil fileUtil = new FileUtil();

        p.setUserNo(loginUser.getUserNo());
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
    @GetMapping("/deletePhoto")
    public Map<String,Object> deletePhoto(@RequestParam int photoNo){
        int check = feedService.deletePhoto(photoNo);
        Map<String,Object> result = new HashMap<>();
        if(check > 0){
            result.put("Java","success");
        }else{
            result.put("Java","fail");
        }
        return result;
    }

    @RequestMapping("/changeValue")
    public Map<String, Object>changeValue(@RequestBody Map<String,Object> doublePhotoNo){
        Map<String,Object> result = new HashMap<>();
        int check = feedService.changeValue(doublePhotoNo);
        if(check>0){
            result.put("Java","success");
        }else{
            result.put("Java", "success");
        }
        return result;
    }

    @RequestMapping("/detailFeed")
    public List<FeedList> detailFeed(@RequestParam int feedNo){
        List<FeedList> list = feedService.detailFeed(feedNo);
        return list;
    }
//
//    @PostMapping("/replyAlarmList")
//    public ResponseBody<List<ReplyAlarmList>> replyAlarmList(@RequestBody Map<String,Object> userNo){
//        List<ReplyAlarmList> list = feedService.replyAlarmList(userNo);
//        return ResponseBuilder.success(list);
//    }


    @PostMapping("/replyAlarmRead")
    public int replyAlarmRead(@RequestBody Map<String, Object> data){
        int result = 0;
        System.out.println("Gdgd" + data);
        result += feedService.replyAlarmRead(data);
        System.out.println(result+"awefawefawef");
        return result;
    }
    @GetMapping("/alarm/list")
    public ResponseBody<List<ReplyAlarmList>> alarmList(@SessionAttribute("loginUser")Profile loginUser){
        List<ReplyAlarmList> list=feedService.replyAlarmList(loginUser.getUserNo());
        if (list==null){return ResponseBuilder.findNothing();}
        return ResponseBuilder.success(list);
    }
    @PostMapping("/replyAlarmCount")
    public int replyAlarmCount(@RequestBody Map<String, Object> userNo){
//        System.out.println(userNo);
        int result = feedService.replyAlarmCount(userNo);
        return result;
    }

    @PostMapping("/heartAlarmList")
    public ResponseBody<List<ReplyAlarmList>> heartAlarmList(@SessionAttribute("loginUser")Profile loginUser){
        List<ReplyAlarmList> list = feedService.heartAlarmList(loginUser.getUserNo());
        if (list==null){return ResponseBuilder.findNothing();}
        return ResponseBuilder.success(list);
    }
    @PostMapping("/heartAlarmRead")
    public int heartAlarmRead(@RequestBody Map<String, Object> data){
        int result = 0;
        result += feedService.heartAlarmRead(data);
        return result;
    }

    @PostMapping("/clearAlarm")
    public int clearAlarm(@RequestBody Map<String, Object> data){
        int result = 0;
        result += feedService.clearAlarm(data);
        return result;
    }



}
