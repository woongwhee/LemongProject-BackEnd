package site.lemongproject.web.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Alias("ChallengeChat")
public class ChallengeChat {
    private int chatNo;
    private int challengeNo;
    private int userNo;
    private String chatMessage;
    private String nickName;
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDateTime sendAt;

    public ChallengeChat(int challengeNo, int userNo, String chatMessage) {
        this.challengeNo = challengeNo;
        this.userNo = userNo;
        this.chatMessage = chatMessage;
    }
}

