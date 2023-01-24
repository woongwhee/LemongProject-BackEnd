package site.lemongproject.web.feed.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dao.FeedDao;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{
    final private FeedDao feedDao;

    @Override
    public ArrayList selectFeed() {
        return null;
    }

    @Override
    public List<Feed> selectMyFeed(){
        return feedDao.selectMyFeed();
    }
}
