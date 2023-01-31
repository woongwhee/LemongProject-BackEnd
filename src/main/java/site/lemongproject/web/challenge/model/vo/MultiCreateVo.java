package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;

import java.time.LocalDate;
@Getter
@Setter
@Alias("MultiCreateVo")
public class MultiCreateVo {
    int userNo;
    int templateNo;
    int challengeNo;
    String challengeTitle;
    String challengeInfo;
    ChallengeOption option;
    ChallengeStatus status;
    LocalDate StartDate;
}
