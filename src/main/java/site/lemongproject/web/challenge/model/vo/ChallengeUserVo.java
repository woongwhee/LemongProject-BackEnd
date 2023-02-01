package site.lemongproject.web.challenge.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Alias("ChallengeUserVo")
public class ChallengeUserVo {
    private int userNo;
    private int challengeNo;
    private ChallengeUserStatus status;
}
