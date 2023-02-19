package site.lemongproject.web.feed.model.service;

import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.feed.model.vo.Reply;
import site.lemongproject.web.feed.model.vo.ReplyAlarm;
import site.lemongproject.web.feed.model.vo.ReplyAlarmList;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Transactional
public interface FeedService  {
    // feed 게시글 가져오기
    List<FeedList> selectFeed(int page);

    // feed 게시글 쓰기
    int insertFeed(FeedInsert paramMap);

    // feed 게시글 업데이트
    int updateFeed(FeedInsert updatefeed);

    // feed 게시글 삭제
    int deleteFeed(Map<String,Object> deleteFeedNo);

    // feed 게시글 댓글 쓰기
    int insertFeedReply(Map<String, Object> paramMap);

    // feed 게시글 댓글 보기
    List<Reply> listReply(int feedNo);

    // feed 게시글 댓글 삭제
    int deleteReply(Map<String,Object> data);

    // 사진 폴더에 넣기
    int insertPhoto(Photo p);

    // 사진 폴더에서 삭제
    int deletePhoto(int photoNo);

    // 사진 수정하기
    int modifyPhoto(Map<String,Object> photoNo);
    
    // 사진 value 수정하기
    int changeValue(Map<String, Object> doublePhotoNo);


    List detailFeed(int feedNo);

    // 댓글수
    int countReply(int feedNo);

    List<FeedList> selectMyFeedList(FeedList f);

    List<FeedList> searchImg(FeedList f);

    // 유저프로필사진
    Map<String, Object> userProfile(Map<String,Object> userNo);

    // 좋아요 누름
    int heartClick(Map<String, Object> data);
    // 좋아요 취소
    int heartCancel(Map<String, Object> data);

    // 좋아요 상태
    int heartState(Map<String, Object> data);

    int heartCount(Map<String, Object> data);

    int countFeed();

    List<ReplyAlarmList> replyAlarmList(Map<String,Object> userNo);

    int replyAlarmRead(Map<String, Object> data);

    int replyAlarmCount(Map<String, Object> userNo);

    List<ReplyAlarmList> heartAlarmList(Map<String, Object> userNo);

    int heartAlarmRead(Map<String, Object> data);

    int clearAlarm(Map<String, Object> data);
}
