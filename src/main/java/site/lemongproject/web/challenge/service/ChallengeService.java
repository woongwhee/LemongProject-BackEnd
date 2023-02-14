package site.lemongproject.web.challenge.service;


import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.model.vo.*;

import java.util.List;

public interface ChallengeService {


    int startSingle(SingleStartVo ssv);

    int joinMulti(ChallengeUserVo msv);

    int leaveMulti(ChallengeUserVo userVo);

    int createMulti(MultiCreateVo msv);

    Challenge selectChallenge(int challengeNo);

    int insertChatData(ChallengeChat chatData);

    List<Challenge> detailChallenge(Challenge c);

    int challengeGo(ChallengeUser u);

    List<ChallengeTodo> calChTodo(ChallengeTodo ct);

    List<ChallengeListVo> getList(int page);

    int clearTodo(TodoClearVo clearVo);
}
