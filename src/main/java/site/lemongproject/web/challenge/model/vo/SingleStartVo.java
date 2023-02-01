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
    private int challengeNo;
    private int userNo;
    private int templateNo;
    private LocalDate startDate;
    private ChallengeStatus status;
    private ChallengeOption option;
    @JsonSetter("option")
    public void setOption(String option) {
        this.option = ChallengeOption.getInstance(option);
    }

}
