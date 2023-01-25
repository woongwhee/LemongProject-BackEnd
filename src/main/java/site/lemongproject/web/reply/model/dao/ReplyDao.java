package site.lemongproject.web.reply.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.reply.model.vo.Reply;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDao {

    final private SqlSession sqlSession;


    public List<Reply> selectFeedReply() {
        return sqlSession.selectList("replyMapper.selectFeedReply");
    }
}
