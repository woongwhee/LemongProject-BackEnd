package site.lemongproject.web.feed.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDao {
    final SqlSession sqlSession;

    public ArrayList selectFeed(){
        return null;
    }

    public List<Feed> selectMyFeed() {
        return sqlSession.selectList("feedMapper.selectMyFeed");
    }
}
