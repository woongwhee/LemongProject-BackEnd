package site.lemongproject.web.template.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class Template {
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
    private TemplateCategory category;
    private Profile create;
    private List<TemplateTodo> todoList;

}
