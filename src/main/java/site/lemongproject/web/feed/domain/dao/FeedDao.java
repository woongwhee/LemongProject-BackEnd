package site.lemongproject.web.feed.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.domain.vo.Feed;
import site.lemongproject.web.feed.domain.vo.Reply;

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

    public List<Reply> listReply(int feedNo){
        return sqlSession.selectList("replyMapper.listReply", feedNo);
    }

    public int updateFeed(Map<String, Object> updatefeed){
        return sqlSession.update("feedMapper.updateFeed", updatefeed);
    }

    public int deleteFeed(Map<String,Object> deleteFeedNo){
        return sqlSession.delete("feedMapper.deleteFeed",deleteFeedNo);
    }
}
