package site.lemongproject.web.feed.domain.service;

import org.springframework.stereotype.Service;
import site.lemongproject.web.feed.domain.dao.FeedDao;

import java.util.ArrayList;

@Service
public class FeedServiceImpl implements FeedService{
    private FeedDao feedDao;

    @Override
    public ArrayList selectFeed() {
        return null;
    }
}
