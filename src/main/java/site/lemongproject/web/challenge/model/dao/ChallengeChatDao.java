package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;

public interface ChallengeChatDao {
    int insertChatData(ChallengeChat chatData);

    ChallengeChat findOne(int chatNo);
}
