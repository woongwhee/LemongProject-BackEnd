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

    int cancelMulti(ChallengeUserVo userVo);

    int createMulti(MultiCreateVo msv);

    Challenge selectChallenge(int challengeNo);

    ChallengeChat insertChatData(ChallengeChat chatData);

    List<Challenge> detailChallenge(Challenge c);

    List<ChallengeTodo> calChTodo(ChallengeTodo ct);

    List<ChallengeListVo> getList(int page);

    List<ChallengeListVo> profileChallengeList(int userNo);

    int clearTodo(TodoClearVo clearVo);

    ChallengeDetailVo getDetail(int challengeNo);

    ChallengeRoomVo getRoomDetail(ChallengeUserVo challengeNo);
}
