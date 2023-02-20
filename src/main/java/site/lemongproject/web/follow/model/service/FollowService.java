package site.lemongproject.web.follow.model.service;

import site.lemongproject.web.follow.model.vo.Follow;

import java.util.List;

public interface FollowService {
    int insertFollow(Follow f);

    List<Follow> selectMyFollowerList(Follow f);

    int updateFollowOk(Follow f);

    int followDelete(Follow f);

    int MyFollowCount(Follow f);

    Follow AcceptFollowCount(Follow f);

    Follow AcceptFollowingCount(Follow f);

    List<Follow> selectMyFollowersdList(Follow f);

    int MyFollowingCount(Follow f);

    List<Follow> selectAcceptFollowingList(Follow f);

    List<Follow> selectAcceptFollowerList(Follow f);

    List<Follow> selectMyFollowingList(Follow f);

    List<Follow> selectMyFollowerAlertList(Follow f);
}
