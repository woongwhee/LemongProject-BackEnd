package site.lemongproject.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.lemongproject.web.challenge.model.dao.ChallengeChatDao;
import site.lemongproject.web.challenge.model.dao.ChallengeDao;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.dto.Challenge;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChallengeScheduler {
    final private ChallengeDao challengeDao;
    final private ChallengeTodoDao  challengeTodoDao;
    final private ChallengeChatDao challengeChatDao;



}
