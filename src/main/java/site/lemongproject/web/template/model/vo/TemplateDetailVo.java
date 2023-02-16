package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.vo.ChallengeListVo;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Alias("TemplateDetailVo")
public class TemplateDetailVo {
    private int templateNo;
    private int categoryNo;
    private Integer range;
    private String title;
    private String content;
    private Date creatAt;
    private boolean saveStatus;//true: 작성완료 false : 임시저장
    private int todoCount;
    private int clearCount;
    private int playCount;
    private boolean isClear;
    private TemplateCategory category;
    private Profile create;
    private List<TemplateTodo> todoList;
    private List<Review> reviewList;//리뷰
    private List<ChallengeListVo> challengeList;//모집중인 첼린지 리스트
}
