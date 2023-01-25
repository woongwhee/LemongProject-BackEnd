package site.lemongproject.web.template.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemplateTodo {
    private long tpTodoNo;
    private int templateNo;
    private int day;
    private String content;
    private int value;
}
