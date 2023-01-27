package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.web.member.model.vo.Profile;
@Getter
@Setter
@ToString
public class ChallengeUser {
    int challengeNo;
    Profile profile;
    int clearCount;
    ChallengeUserStatus status;
}
