package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.EndDateUpdateVo;
import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MyBatisChallengeDao implements ChallengeDao{
    final private SqlSession session;

    @Override
    public int insertSingle(SingleStartVo startVo) {
        return session.insert("challengeMapper.insertSingle",startVo);
    }

    @Override
    public int insertMulti(MultiCreateVo createVo) {
        return session.insert("challengeMapper.insertMulti",createVo);
    }

    @Override
    public int joinUser(ChallengeUserVo challengeUserVo) {
        return session.insert("challengeMapper.joinUser",challengeUserVo);
    }

    @Override
    public int updateEndDate(EndDateUpdateVo endDateUpdateVo) {
        return session.update("challengeMapper.updateEndDate",endDateUpdateVo);
    }

    @Override
    public int deleteUser(ChallengeUserVo userVo) {
        return 0;
    }

    @Override
    public Challenge selectChallenge(Challenge cNo){
        return session.selectOne("challengeMapper.selectChallenge" , cNo);
    }
}
