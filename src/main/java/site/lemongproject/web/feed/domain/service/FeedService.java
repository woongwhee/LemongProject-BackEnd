package site.lemongproject.web.feed.domain.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface FeedService  {

    // feed 게시글 가져오기
    public ArrayList selectFeed();



}
