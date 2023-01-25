package site.lemongproject.web.feed.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.reply.model.vo.Reply;

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

    public int deleteReply(Map<String,Object> data){
        return sqlSession.delete("replyMapper.deleteReply", data);
    }
}
