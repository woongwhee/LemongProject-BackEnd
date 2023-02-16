package site.lemongproject.web.challenge.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.SessionAttribute;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;

import java.sql.Date;
import java.time.LocalDate;

@Alias("SingleStartVo")
@Getter
@Setter
@ToString
public class SingleStartVo {
    @JsonProperty
    private int templateNo;
    @JsonFormat(pattern = "yyMMdd", timezone = "GMT+9")
    @JsonProperty
    private LocalDate startDate;
    @JsonProperty
    private String challengeTitle;
    @JsonProperty
    private ChallengeOption option;
    private int challengeNo;
    private int userNo;
    private ChallengeStatus status;
    @JsonSetter("option")
    public void setOption(String option) {
        this.option = ChallengeOption.getInstance(option);
    }
}
