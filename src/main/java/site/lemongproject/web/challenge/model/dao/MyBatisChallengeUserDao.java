package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.model.vo.ChallengeListVo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisChallengeUserDao implements ChallengeUserDao {
    final private SqlSession sqlSession;

    @Override
    public int joinUser(ChallengeUserVo joinVo) {
        return sqlSession.insert("challengeUserMapper.joinUser", joinVo);
    }

    @Override
    public int changeClear(ChallengeTodo todo) {
        return sqlSession.update("challengeUserMapper.changeClear", todo);
    }


    @Override
    public int cancelUser(ChallengeUserVo userVo) {
        return sqlSession.update("challengeUserMapper.cancelUser", userVo);
    }

    @Override
    public int deleteUser(ChallengeUserVo userVo) {
        return sqlSession.update("challengeUserMapper.deleteUser", userVo);
    }

    @Override
    public int countPlayer(int challengeNo) {
        return sqlSession.selectOne("challengeUserMapper.countPlayer", challengeNo);
    }

    @Override
    public boolean inChallenge(ChallengeUserVo userVo) {
        return sqlSession.selectOne("challengeUserMapper.inChallenge", userVo);
    }


    @Override
    public int finishChallenge(float CLEAR_PERCENT) {
        return sqlSession.update("challengeUserMapper.challengeFinish", CLEAR_PERCENT);
    }

    @Override
    public int adjustClearCount() {
        return sqlSession.update("challengeUserMapper.adjustClearCount");
    }

    @Override
    public int startChallenge() {
        return sqlSession.update("challengeUserMapper.challengeStart");
    }

}
