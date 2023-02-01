package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dto.Template;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Alias("Challenge")
public class Challenge {
    private int challengeNo;
    private Profile createUser;
    private int templateNo;
    private String ChallengeTitle;
    private String ChallengeInfo;
    private LocalDate StartDate;
    private LocalDate endDate;
    private String ChallengeOption;
    private ChallengeStatus status;
}
