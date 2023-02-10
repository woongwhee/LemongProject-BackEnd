//package site.lemongproject.common.scheduler;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import site.lemongproject.web.challenge.model.dao.ChallengeChatDao;
//import site.lemongproject.web.challenge.model.dao.ChallengeDao;
//import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
//import site.lemongproject.web.challenge.model.dto.Challenge;
//import site.lemongproject.web.challenge.service.ChallengeService;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ChallengeScheduler {
//    final private ChallengeDao challengeDao;
//    final private ChallengeTodoDao todoDao;
//    @Scheduled(cron = "0 0 0 1/1 * ?",zone = "Asia/Seoul")
//    @Transactional
//    //챌린지시작
//    public int challengeStart(){
////        challengeDao.startChallenge();
//
//
//
//
//        return 0;
//    }
//    //챌린지 결과 입력
//    @Scheduled(cron = "0 0 0 1/1 * ?",zone = "Asia/Seoul")
//    public int challengeFinish(){
//
//        return 0;
//    }
//
//}
