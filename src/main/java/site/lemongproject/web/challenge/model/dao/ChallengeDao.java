package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.model.vo.*;

import java.util.List;

public interface ChallengeDao {
    int insertSingle(SingleStartVo startVo);

    int insertMulti(MultiCreateVo createVo);

    int updateEndDate(EndDateUpdateVo endDateUpdateVo);


    List<Challenge> selectChallenge();

    List<Challenge> detailChallenge(Challenge c);

    int challengeGo(ChallengeUser u);

    Challenge findOne(int challengeNo);

    List<ChallengeListVo> findReady(int page, int limit);
    ChallengeDetailVo findDetail(int challengeNo);
    ChallengeRoomVo findRoom(int challengeNo);
    int cancelChallenge(int challengeNo);

    int startChallenge();
    int finishChallenge();

}
