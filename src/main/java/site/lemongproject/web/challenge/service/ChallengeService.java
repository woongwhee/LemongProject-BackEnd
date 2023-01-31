package site.lemongproject.web.challenge.service;


import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.MultiJoinVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;

public interface ChallengeService {


    int startSingle(SingleStartVo ssv);
    int joinMulti(MultiJoinVo msv);
    int createMulti(MultiCreateVo msv);

}
