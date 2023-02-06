package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;

@RequiredArgsConstructor
@Repository
public class MyBatisChallengeChatDao implements ChallengeChatDao{
    final private SqlSession session;

    public int insertChatData(ChallengeChat chatData){
        return session.insert("challengeChatMapper.insertChatData" , chatData);
    }

}
