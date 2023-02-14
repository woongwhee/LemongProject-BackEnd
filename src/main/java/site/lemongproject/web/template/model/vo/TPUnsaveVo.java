package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.util.List;

@Alias("TPUnsaveVo")
@Getter
@Setter
public class TPUnsaveVo {
    private int userNo;
    private int templateNo;
    private Integer categoryNo;
    private Integer range;
    private String title;
    private String content;
    private List<TemplateTodo> todoList;
}
