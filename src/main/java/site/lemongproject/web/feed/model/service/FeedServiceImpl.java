package site.lemongproject.web.feed.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dao.FeedDao;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.photo.model.dao.PhotoDao;
import site.lemongproject.web.feed.model.dto.FeedInsertPhoto;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{
    final private FeedDao feedDao;
    final private PhotoDao photoDao;

    @Override
    public List selectFeed() {
        return feedDao.selectFeed();
    }

    // 피드 게시물 등록
    @Override
    public int insertFeed(FeedInsert paramMap){
        int result = feedDao.insertFeed(paramMap); // 피드에 정보 넣기(feedNo, userNo, feedContent, photoNo)
                                                    // FeedInsert(userNo=3, feedContent=마지막테스트, photoNo=[98, 99], feedNo=0)
        for (int i =0; i<paramMap.getPhotoNo().size(); i++){
            result *= feedDao.insertFeedPhoto(new FeedInsertPhoto(paramMap.getFeedNo(), paramMap.getPhotoNo().get(i),i+1));
        }
        return result;
    }
    // 피드 업데이트
    @Override
    public int updateFeed(Map<String,Object> updatefeed){
        return feedDao.updateFeed(updatefeed);
    };

    // 피드 삭제
    @Override
    public int deleteFeed(Map<String,Object> deleteFeedNo){
        return feedDao.deleteFeed(deleteFeedNo);
    }

    // 피드 댓글 등록
    @Override
    public int insertFeedReply(Map<String, Object> paramMap){
        return feedDao.insertFeedReply(paramMap);
    }

    // 피드 댓글 불러오기
    @Override
    public List listReply(int feedNo) {return feedDao.listReply(feedNo);}

    // 피드 댓글 삭제
    @Override
    public int deleteReply(Map<String,Object> data){
        return feedDao.deleteReply(data);
    }

    // 사진 넣기
    @Override
    public int insertFeedPhoto(Photo p){
        return photoDao.insertPhoto(p);
    }

    // 사진 지우기
    @Override
    public int deletePhoto(int photoNo){
        return photoDao.deletePhoto(photoNo);
    }

}
