package site.lemongproject.web.template.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Getter
@AllArgsConstructor
@Alias("TPDayTodoVo")
public class TPDayTodoVo {
    int templateNo;
    int day;
}
