package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.EndDateUpdateVo;
import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;

import java.util.List;

public interface ChallengeDao {
    int insertSingle(SingleStartVo startVo);
    int insertMulti(MultiCreateVo createVo);
    int joinUser(ChallengeUserVo joinVo);
    int updateEndDate(EndDateUpdateVo endDateUpdateVo);
    int deleteUser(ChallengeUserVo userVo);

    Challenge selectChallenge(Challenge cNo);
}
