package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.web.member.model.vo.Profile;
@Getter
@Setter
@ToString
@Alias("ChallengeUser")
public class ChallengeUser {
    private int challengeNo;
    private int userNo;
    private Profile profile;
    private int clearCount;
    private ChallengeUserStatus status;
    private Challenge challenge;
}