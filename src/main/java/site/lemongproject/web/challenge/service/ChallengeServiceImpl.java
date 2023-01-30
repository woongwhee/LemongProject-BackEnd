package site.lemongproject.web.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.challenge.model.dao.ChallengeDao;
import site.lemongproject.web.challenge.model.dto.Challenge;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService{
    final private ChallengeDao challengeDao;



}
