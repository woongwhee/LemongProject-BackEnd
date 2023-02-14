package site.lemongproject.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.challenge.model.dao.ChallengeChatDao;
import site.lemongproject.web.challenge.model.dao.ChallengeDao;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.dao.ChallengeUserDao;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.service.ChallengeService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChallengeScheduler {
    final private ChallengeDao challengeDao;
    final private ChallengeTodoDao todoDao;
    final private ChallengeUserDao userDao;
    private final float CLEAR_PERCENT=0.9f;
    @Scheduled(cron = "0 59 23 1/1 * ?",zone = "Asia/Seoul")
    @Transactional
    //챌린지시작
    public int challengeStart(){
        int result=challengeDao.startChallenge();
        return result*userDao.startChallenge();
    }
    //챌린지 결과 입력
    @Scheduled(cron = "0 59 23 1/1 * ?",zone = "Asia/Seoul")
    public int challengeFinish(){
        int result=userDao.finishChallenge(CLEAR_PERCENT);
        result*=challengeDao.finishChallenge();
        return 0;

    }
    @Scheduled(cron ="0 0 4 1/1 * ?")
    public int adjustClearCount(){
        return userDao.adjustClearCount();

    }
}
