package site.lemongproject.web.challenge.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;

import java.time.LocalDate;
@Getter
@Setter
@Alias("MultiCreateVo")
public class MultiCreateVo extends SingleStartVo{
    @JsonProperty
    private String challengeInfo;
}
