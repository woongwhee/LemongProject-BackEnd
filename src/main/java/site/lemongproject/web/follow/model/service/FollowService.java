package site.lemongproject.web.follow.model.service;

import site.lemongproject.web.follow.model.vo.Follow;

import java.util.List;

public interface FollowService {
    int insertFollow(Follow f);

    List<Follow> selectMyFollowerList(Follow f);

    int updateFollowOk(Follow f);

    int followDelete(Follow f);

    Follow MyFollowCount(Follow f);

    Follow AcceptFollowCount(Follow f);

    Follow AcceptFollowingCount(Follow f);
}
