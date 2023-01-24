package site.lemongproject.web.feed.model.service;

import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface FeedService  {

    // feed 게시글 가져오기
    public ArrayList selectFeed();


    public List<Feed> selectMyFeed();
}
