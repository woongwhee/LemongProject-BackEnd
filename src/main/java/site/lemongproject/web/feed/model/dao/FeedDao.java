package site.lemongproject.web.feed.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.model.dto.FeedInsertPhoto;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.reply.model.vo.Reply;


import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FeedDao {
    final private SqlSession sqlSession;

    // 피드 메인
    public List<Feed> selectFeed(){
        return  sqlSession.selectList("feedMapper.selectFeed");
    }
    // 피드 게시물 작성
    public int insertFeed(FeedInsert paramMap){
        return sqlSession.insert("feedMapper.insertFeed", paramMap);
    }
    // 피드 사진 넣기
    public int insertFeedPhoto(FeedInsertPhoto feedPhoto) {
        return sqlSession.insert("feedMapper.insertFeedPhoto",feedPhoto);
    }
    // 피드 게시물 수정
    public int updateFeed(Map<String, Object> updatefeed){
        return sqlSession.update("feedMapper.updateFeed", updatefeed);
    }
    // 피드 게시물 삭제
    public int deleteFeed(Map<String,Object> deleteFeedNo){
        return sqlSession.delete("feedMapper.deleteFeed",deleteFeedNo);
    }

    // 피드 댓글 삭제
    public int deleteReply(Map<String,Object> data){
        return sqlSession.delete("replyMapper.deleteReply", data);
    }
    // 피드 댓글 불러오기
    public List<Reply> listReply(int feedNo){
        return sqlSession.selectList("replyMapper.listReply", feedNo);
    }
    // 피드 댓글 넣기
    public int insertFeedReply(Map<String,Object> paramMap){
        return sqlSession.insert("replyMapper.insertFeedReply", paramMap);
    }

    // 사진 등록
    public int insertPhoto(Photo p){
        return sqlSession.insert("feedMapper.insertPhoto", p);
    }
    // 사진 지우기
    public int deletePhoto(int photoNo){
        return sqlSession.delete("feedMapper.deletePhoto", photoNo);
    }

}
