package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dto.TemplateCategory;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Alias("ChallengeListVo")
public class ChallengeListVo {
    private int challengeNo;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int userCount;
    private ChallengeUserStatus status;
    private Profile createUser;
    private TemplateCategory category;

}
