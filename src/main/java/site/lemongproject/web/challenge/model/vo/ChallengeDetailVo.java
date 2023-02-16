package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.member.model.vo.Profile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Alias("ChallengeDetailVo")
@ToString
public class ChallengeDetailVo {
    private int challengeNo;
    private String challengeTitle;
    private String challengeInfo;
    private LocalDate startDate;
    private LocalDate endDate;
    private ChallengeTodoVo todoPreview;
    List<Profile> readyUsers;
}
