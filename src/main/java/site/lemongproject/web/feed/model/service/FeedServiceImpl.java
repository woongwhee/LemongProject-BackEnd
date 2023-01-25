package site.lemongproject.web.feed.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dao.FeedDao;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{
    final private FeedDao feedDao;

    @Override
    public List selectFeed() {
        return feedDao.selectFeed();
    }

    @Override
    public int insertFeed(Map<String, Object> paramMap){
        return feedDao.insertFeed(paramMap);
    }

    @Override
    public int insertFeedReply(Map<String, Object> paramMap){
        return feedDao.insertFeedReply(paramMap);
    }

    @Override
    public List listReply(int feedNo) {return feedDao.listReply(feedNo);}

    @Override
    public int updateFeed(Map<String,Object> updatefeed){
        return feedDao.updateFeed(updatefeed);
    };

    @Override
    public int deleteFeed(Map<String,Object> deleteFeedNo){
        return feedDao.deleteFeed(deleteFeedNo);
    }

    @Override
    public int deleteReply(Map<String,Object> data){
        return feedDao.deleteReply(data);
    }
}
