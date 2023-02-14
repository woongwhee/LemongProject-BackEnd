package site.lemongproject.web.feed.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.feed.model.dto.FeedDetail;
import site.lemongproject.web.feed.model.dto.FeedInsertPhoto;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.feed.model.vo.Reply;


import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FeedDao {
    final private SqlSession sqlSession;

    // 피드 메인
    public List<FeedList> selectFeed(){
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
    public int updateFeed(FeedInsert updatefeed){
        return sqlSession.update("feedMapper.updateFeed", updatefeed);
    }
    // 피드 게시물 삭제
    public int deleteFeed(Map<String,Object> deleteFeedNo){
        return sqlSession.delete("feedMapper.deleteFeed",deleteFeedNo);
    }
    // 피드 게시물삭제
    public int deleteFeedPhotoFeedNo(Map<String, Object> deleteFeedNo){
        return sqlSession.delete("feedMapper.deleteFeedPhotoFeedNo", deleteFeedNo);
    }
    // 피드 업데이트 하기 전 삭제
    public int deleteFeedPhotoFeedNo2(FeedInsert deleteFeedNo){
        return sqlSession.delete("feedMapper.deleteFeedPhotoFeedNo", deleteFeedNo);
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

    // 사진 피드 삭제하기
//    public int modifyPhoto(String filePath){
//        return sqlSession.update("feedMapper.modifyPhoto", filePath);
//    }
    public int maxValue(Map<String,Object> photoNo){
        return sqlSession.selectOne("feedMapper.maxValue",photoNo);
    }
    public int nowValue(Map<String,Object> photoNo){
        return sqlSession.selectOne("feedMapper.nowValue",photoNo);
    }

    public int updateValueFirst(Map<String,Object>photoNo){
        return sqlSession.update("feedMapper.updateValueFirst",photoNo);
    }

    public int updateValueMiddle(Map<String,Object>photoNo) {
        return sqlSession.update("feedMapper.updateValueMiddle",photoNo);
    }

    public int updateValueLast(Map<String,Object>photoNo){
        return sqlSession.update("feedMapper.updateValueLast",photoNo);

    }
    public int modifyPhoto(Map<String,Object>photoNo){
        return sqlSession.delete("feedMapper.deleteFeedPhoto",photoNo);
    }

    // 현재 value
    public int startValue(Map<String, Object> doublePhotoNo) {
        return sqlSession.selectOne("feedMapper.startValue",doublePhotoNo);
    }

    public int finishValue(Map<String, Object> doublePhotoNo) {
        return sqlSession.selectOne("feedMapper.finishValue",doublePhotoNo);
    }
    // value 바꾸기
    public int updateStartValue(Map<String, Object> doublePhotoNo) {
        return sqlSession.update("feedMapper.updateStartValue",doublePhotoNo);
    }

    public int updateFinishValue(Map<String, Object> doublePhotoNo) {
        return sqlSession.update("feedMapper.updateFinishValue",doublePhotoNo);
    }


    public List<FeedList> FeedDetail(int feedNo) {
        return sqlSession.selectList("feedMapper.detailFeed", feedNo);
    }

    public int countReply(int feedNo) {
        return sqlSession.selectOne("replyMapper.countReply", feedNo);
    }

    public List<FeedList> selectMyFeedList(FeedList f) {
        return sqlSession.selectList("feedMapper.selectMyFeed" , f);
    }

    public List<FeedList> searchImg(FeedList f) {
        return sqlSession.selectList("feedMapper.searchImg" , f);
    }
}
