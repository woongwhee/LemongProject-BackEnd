package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@ToString
public class ChallengeChat {
    int chatNo;
    int challengeNo;
    int userNo;
    String chatMessage;
    Date sendAt;
}

