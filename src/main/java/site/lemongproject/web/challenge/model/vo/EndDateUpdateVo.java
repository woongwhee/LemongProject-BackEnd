package site.lemongproject.web.challenge.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

@Getter
@Setter
@Alias("EndDateUpdateVo")
public class EndDateUpdateVo {
    int challengeNo;
    LocalDate endDate;
}
