package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;
@Getter
@Setter
@Alias("MultiJoinVo")
public class MultiJoinVo {
    int userNo;
    int challengeNo;
}
