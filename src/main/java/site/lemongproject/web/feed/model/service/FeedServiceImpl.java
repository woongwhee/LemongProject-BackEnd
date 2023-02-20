package site.lemongproject.web.feed.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dao.FeedDao;
import site.lemongproject.web.feed.model.dto.*;
import site.lemongproject.web.feed.model.vo.HeartAlarm;
import site.lemongproject.web.feed.model.vo.Reply;
import site.lemongproject.web.feed.model.vo.ReplyAlarm;

import site.lemongproject.web.feed.model.vo.ReplyAlarmList;
import site.lemongproject.web.photo.model.dao.PhotoDao;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor

public class FeedServiceImpl implements FeedService{
    final private FeedDao feedDao;
    final private PhotoDao photoDao;
    final static int PAGE_LIMIT=2;
    @Override
    public Map<String, Object> userProfile(Map<String,Object> userNo){
        return feedDao.userProfile(userNo);
    };

    @Override
    public List<FeedList> selectFeed(int page) {
        return feedDao.selectFeed(page,PAGE_LIMIT);
    }

    // 피드 게시물 등록
    @Override
    public int insertFeed(FeedInsert paramMap){
        int result = feedDao.insertFeed(paramMap); // 피드에 정보 넣기(feedNo, userNo, feedContent, photoNo)
                                                    // FeedInsert(userNo=3, feedContent=마지막테스트, photoNo=[98, 99], feedNo=0)
        for (int i =0; i<paramMap.getPhotoNo().size(); i++){
            result *= feedDao.insertFeedPhoto(new FeedInsertPhoto(paramMap.getFeedNo(), paramMap.getPhotoNo().get(i),i+1));
        }
        return result;
    }
    // 피드 업데이트
    @Override
    public int updateFeed(FeedInsert updatefeed){
        int result = 0;
        if(updatefeed.getFeedContent().equals("")){
            result += 1;
        }else{
            result +=feedDao.updateFeed(updatefeed); // 내용 업데이트
        }
        result += feedDao.deleteFeedPhotoFeedNo2(updatefeed); // 피드 수정하기전 사진삭제

        for (int i =0; i<updatefeed.getPhotoNo().size(); i++){
            result += feedDao.insertFeedPhoto(new FeedInsertPhoto(updatefeed.getFeedNo(), updatefeed.getPhotoNo().get(i), i+1));
        }
        return result;
    };

    // 피드 삭제
    @Override
    public int deleteFeed(Map<String,Object> deleteFeedNo){
        int result =  feedDao.deleteFeedPhotoFeedNo(deleteFeedNo);
        if(result > 0){
            result *= feedDao.deleteFeed(deleteFeedNo);
        }else {
            result *= 0;
        }
        return result;
    }

    // 피드 댓글 등록
    @Override
    public int insertFeedReply(Map<String, Object> paramMap){
        // param = 로그인NO(보낸사람)(loginUserNo), feedNo, replyContent
        int result = feedDao.insertFeedReply(paramMap); // 댓글 등록
        ReplyAlarm ra = feedDao.selectReplyAlarm(paramMap);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(ra, Map.class);

        if(ra.getUserNo() != ra.getRecNo()){
            result += feedDao.insertReplyAlarm(map);
            return result;
        }else{
            return result;
        }
    }
    // 피드 댓글 불러오기
    @Override
    public List<Reply> listReply(int feedNo) {return feedDao.listReply(feedNo);}

    // 피드 댓글 삭제
    @Override
    public int deleteReply(Map<String,Object> data){
        return feedDao.deleteReply(data);
    }



    // 사진 넣기
    @Override
    public int insertPhoto(Photo p){
        return photoDao.insertPhoto(p);
    }

    // 사진 지우기
    @Override
    public int deletePhoto(int photoNo){
        return photoDao.deletePhoto(photoNo);
    }

    // 사진 피드 수정하기
    @Override
    public int modifyPhoto(Map<String,Object> photoNo){
        int maxValue = feedDao.maxValue(photoNo);  // 4
        int nowValue = feedDao.nowValue(photoNo);  // 2
        System.out.println("maxValue : " + maxValue);
        System.out.println("nowValue : "+nowValue);

        int result = 0;

        if(nowValue == 1){
            result += feedDao.updateValueFirst(photoNo);
        } else if (1 < nowValue && nowValue < maxValue) {
            result += feedDao.updateValueMiddle(photoNo);
        }
        result*=feedDao.modifyPhoto(photoNo);
            return result;

    }
    // 사진 value 수정하기
    @Override
    public int changeValue(Map<String, Object> doublePhotoNo){
        int startValue = feedDao.startValue(doublePhotoNo); // 1
        int finishValue = feedDao.finishValue(doublePhotoNo); // 2
        int result = 0;
        doublePhotoNo.put("startValue",startValue);
        doublePhotoNo.put("finishValue",finishValue);
        System.out.println(doublePhotoNo);
        result += feedDao.updateStartValue(doublePhotoNo);
        result += feedDao.updateFinishValue(doublePhotoNo);
        return result;
    }

    @Override
    public List<FeedList> detailFeed(int feedNo){
        return feedDao.FeedDetail(feedNo);
    }

    @Override
    public int countReply(int feedNo){
        return feedDao.countReply(feedNo);
    }

    @Override
    public List<FeedList> selectMyFeedList(FeedList f){
        return feedDao.selectMyFeedList(f);
    }

    public List<FeedList> searchImg(FeedList f){
        return feedDao.searchImg(f);
    }

    @Override
    public int heartClick(Map<String, Object> data){
        int result = feedDao.heartClick(data);
        HeartAlarm ha = new HeartAlarm();

        ha.setRecNo(feedDao.heartAlarmReceiver(data));
        int userNo = (int)data.get("userNo");
        int feedNo = (int)data.get("refNo");
        ha.setUserNo(userNo);
        ha.setFeedNo(feedNo);
        System.out.println("fdfdf" + ha);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(ha, Map.class);

        if(ha.getUserNo() != ha.getRecNo()){
            result += feedDao.heartAlarmInsert(map);
            System.out.println("ekfoeofke" + result);
            return result;
        }else{
            return result;
        }
    }

    @Override
    public int heartCancel(Map<String, Object> data){
        int result = feedDao.heartCancel(data);
        result += feedDao.heartAlarmCancel(data);
        return result;
    }

    @Override
    public int heartState(Map<String, Object> data) {return feedDao.heartState(data);}

    @Override
    public int heartCount(Map<String, Object> data){return feedDao.heartCount(data);}

    @Override
    public int countFeed() {
       int feedCount=feedDao.countFeed();
       if(feedCount%PAGE_LIMIT==0){
           return feedCount/PAGE_LIMIT-1;
       }else{
           return feedCount/PAGE_LIMIT;
       }
    }

    @Override
    public List<ReplyAlarmList> replyAlarmList(Map<String,Object> userNo) {
        return feedDao.replyAlarmList(userNo);
    }
    @Override
    public int replyAlarmRead(Map<String, Object> data) {
        return feedDao.replyAlarmRead(data);
    }
    @Override
    public int replyAlarmCount(Map<String, Object> userNo) {
        return feedDao.replyAlarmCount(userNo);
    }
    @Override
    public List<ReplyAlarmList> heartAlarmList(Map<String, Object> userNo) {
        return feedDao.heartAlarmList(userNo);
    }

    @Override
    public int heartAlarmRead(Map<String, Object> data) {
        return feedDao.heartAlarmRead(data);
    }

    @Override
    public int clearAlarm(Map<String, Object> data) {
        return feedDao.clearAlarm(data);
    }
}
