package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
@Getter
@Setter
@Alias("CGTodoItemVo")
public class CGTodoItemVo {
    private LocalDate todoDate;
    private String todoContent;
    private int value;
}
