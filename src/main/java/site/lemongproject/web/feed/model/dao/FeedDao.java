package site.lemongproject.web.feed.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.model.vo.Feed;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FeedDao {

    final private SqlSession sqlSession;

    public List<Feed> selectFeed(){
        return  sqlSession.selectList("feedMapper.selectFeed");
    }

    public int insertFeed(Map<String,Object> paramMap){
        return sqlSession.insert("feedMapper.insertFeed", paramMap);
    }

    public int insertFeedReply(Map<String,Object> paramMap){
        return sqlSession.insert("replyMapper.insertFeedReply", paramMap);
    }
}
