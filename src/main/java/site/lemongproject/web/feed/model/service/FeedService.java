package site.lemongproject.web.feed.model.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public interface FeedService  {
    // feed 게시글 가져오기
    List selectFeed();
    // feed 게시글 쓰기
    int insertFeed(Map<String, Object> paramMap);

    // feed 게시글 댓글 쓰기
    int insertFeedReply(Map<String, Object> paramMap);

    // feed 게시글 댓글 보기
    List listReply(int feedNo);

    // feed 게시글 업데이트
    int updateFeed(Map<String,Object> updatefeed);

    // feed 게시글 삭제
    int deleteFeed(Map<String,Object> deleteFeedNo);


}
