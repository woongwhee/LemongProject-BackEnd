package site.lemongproject.web.feed.model.service;

import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Transactional
public interface FeedService  {
    // feed 게시글 가져오기
    List<FeedList> selectFeed();

    // feed 게시글 쓰기
    int insertFeed(FeedInsert paramMap);

    // feed 게시글 업데이트
    int updateFeed(Map<String,Object> updatefeed);

    // feed 게시글 삭제
    int deleteFeed(Map<String,Object> deleteFeedNo);


    // feed 게시글 댓글 쓰기
    int insertFeedReply(Map<String, Object> paramMap);

    // feed 게시글 댓글 보기
    List listReply(int feedNo);

    // feed 게시글 댓글 삭제
    int deleteReply(Map<String,Object> data);

    // 사진 폴더에 넣기
    int insertPhoto(Photo p);

    // 사진 폴더에서 삭제
    int deletePhoto(int photoNo);

    // 사진 수정하기
    int modifyPhoto(Map<String,Object> photoNo);

}
