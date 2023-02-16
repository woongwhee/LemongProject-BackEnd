package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;

import java.util.List;

@Alias("ChallengeRoomVo")
@Getter
@Setter
public class ChallengeRoomVo {
    private int challengeNo;
    private String challengeTitle;
    private int todoCount;
    private List<ChatProfileVo> playerList;
    private List<ChallengeChat> chatList;
}
