package site.lemongproject.web.template.model.dto;

import lombok.Data;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Date;
import java.util.List;
@Data
public class Template {
    private int templateNo;
    private Profile profile;
    private int range;
    private String title;
    private String content;
    private Date creatAt;
    private boolean saveStatus;//true: 작성완료 false : 임시저장
    private int todoCount;
    private int clearCount;
    private int challengeCount;
    private List<TemplateTodo> todoList;
}
