package site.lemongproject.web.template.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("TemplateTodo")
public class TemplateTodo {
    private long tpTodoNo;
    private int templateNo;
    private int day;
    private String content;
    private int value;
}
