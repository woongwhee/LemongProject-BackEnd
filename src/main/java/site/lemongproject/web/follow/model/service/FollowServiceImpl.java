package site.lemongproject.web.follow.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.follow.model.dao.FollowDao;
import site.lemongproject.web.follow.model.vo.Follow;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    final private FollowDao followDao;

    public int insertFollow(Follow f){
        return followDao.insertFollow(f);
    }

    public List<Follow> selectMyFollowerList(Follow f){
        return followDao.selectMyFollowerList(f);
    }

    public int updateFollowOk(Follow f){
        return followDao.updateFollowOk(f);
    }

    public int followDelete(Follow f){
        return followDao.followDelete(f);
    }

    public Follow MyFollowCount(Follow f){
        return followDao.MyFollowCount(f);
    }

    public Follow AcceptFollowCount(Follow f){
        return followDao.AcceptFollowCount(f);
    }

    public Follow AcceptFollowingCount(Follow f){
        return followDao.AcceptFollowingCount(f);
    }
}
