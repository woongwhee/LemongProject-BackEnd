package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.vo.EndDateUpdateVo;
import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;

public interface ChallengeDao {
    int insertSingle(SingleStartVo startVo);
    int insertMulti(MultiCreateVo createVo);

    int updateEndDate(EndDateUpdateVo endDateUpdateVo);

}
