package site.lemongproject.web.challenge.model.vo;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;

import java.sql.Date;
import java.time.LocalDate;

@Alias("SingleStartVo")
@Getter
@Setter
public class SingleStartVo {
    int userNo;
    int templateNo;
    int challengeNo;
    LocalDate startDate;
    ChallengeStatus status;
    ChallengeOption option;
    @JsonSetter("option")
    public void setOption(String option) {
        this.option = ChallengeOption.getInstance(option);
    }

}
