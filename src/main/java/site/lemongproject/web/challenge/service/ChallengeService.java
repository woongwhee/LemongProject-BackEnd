package site.lemongproject.web.challenge.service;


import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;

public interface ChallengeService {


    int startSingle(SingleStartVo ssv);
    int joinMulti(ChallengeUserVo msv);

    int leaveMulti(ChallengeUserVo userVo);

    int createMulti(MultiCreateVo msv);

}
