package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;

@Repository
@RequiredArgsConstructor
public class MyBatisChallengeUserDao implements ChallengeUserDao{
    final private SqlSession sqlSession;
    @Override
    public int joinUser(ChallengeUserVo joinVo) {
        return sqlSession.insert("challengeUserMapper.joinUser",joinVo);
    }
    @Override
    public int changeClear(ChallengeTodo todo) {
        return sqlSession.update("challengeUserMapper.changeClear",todo);
    }
    @Override
    public int adjustClearCount(){
        return sqlSession.update("challengeUserMapper.adjustClearCount");
    }

}
