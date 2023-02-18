package site.lemongproject.web.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Alias("ChallengeChat")
public class ChallengeChat {
    private int chatNo;
    private int challengeNo;
    private int userNo;
    private String chatMessage;
    private LocalDateTime sendAt;
}

