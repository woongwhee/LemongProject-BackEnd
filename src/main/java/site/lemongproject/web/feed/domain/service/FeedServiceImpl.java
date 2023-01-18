package site.lemongproject.web.feed.domain.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.domain.dao.FeedDao;
import site.lemongproject.web.feed.domain.vo.Feed;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional // 트래직션 처리해줌
@RequiredArgsConstructor // final붙은거 생성자로
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
}
