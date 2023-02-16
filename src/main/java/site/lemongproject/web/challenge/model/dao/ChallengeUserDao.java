package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;

public interface ChallengeUserDao {
    int joinUser(ChallengeUserVo joinVo);
    int changeClear(ChallengeTodo todo);
    int adjustClearCount();
    int finishChallenge(float clear_percent);
    int startChallenge();

}
