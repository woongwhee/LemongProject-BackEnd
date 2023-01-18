package site.lemongproject.web.feed.domain.service;

import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.domain.vo.Feed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public interface FeedService  {

    // feed 게시글 가져오기
    List selectFeed();
    // feed 게시글 쓰기
    int insertFeed(Map<String, Object> paramMap);



}
