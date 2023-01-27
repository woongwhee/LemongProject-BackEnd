package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dto.Template;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Challenge {
    int challengeNo;
    Profile createUser;
    Template templateNo;
    String ChallengeTitle;
    String ChallengeInfo;
    Date StartDate;
    Date endDate;
    String ChallengeOption;
    ChallengeStatus status;
}
