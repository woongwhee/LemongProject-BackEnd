package site.lemongproject.web.challenge.model.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("ChallengeUserVo")
public class ChallengeUserVo {
    private int userNo;
    private int challengeNo;
    private ChallengeUserStatus status;
}
