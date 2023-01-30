package site.lemongproject.web.feed.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.model.dao.FeedDao;
import site.lemongproject.web.feed.model.dto.FeedInsertPhoto;
import site.lemongproject.web.feed.model.dto.FeedInsert;
import site.lemongproject.web.feed.model.dto.FeedList;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService{
    final private FeedDao feedDao;
    
    // 피드 게시물 
    @Override
    public List<FeedList> selectFeed() {
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
    public int insertPhoto(Photo p){
        return feedDao.insertPhoto(p);
    }
    
    // 사진 지우기
    @Override
    public int deletePhoto(int photoNo){
        return feedDao.deletePhoto(photoNo);
    }

    // 사진 피드 수정하기
    @Override
    public int modifyPhoto(Map<String,Object> photoNo){
        int maxValue = feedDao.maxValue(photoNo);  // 4
        int nowValue = feedDao.nowValue(photoNo);  // 2
        System.out.println(maxValue);
        System.out.println(nowValue);

        int result = 1;

        if(nowValue == 1){
            result *= feedDao.updateValueFirst(photoNo);
        } else if (1 < nowValue && nowValue < maxValue) {
            result *= feedDao.updateValueMiddle(photoNo);
        }else{
            result *= feedDao.updateValueLast(photoNo);
        }
        if(result > 1){
            return feedDao.modifyPhoto(photoNo);
        }else{
            return result*0;
        }
    };
}
