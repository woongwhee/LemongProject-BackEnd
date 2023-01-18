package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateTodo {
    private long tpTodoNo;
    private int templateNo;
    private int day;
    private String content;
    private int value;
}
