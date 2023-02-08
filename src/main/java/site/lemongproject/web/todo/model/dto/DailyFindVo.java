package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
@Getter
@Setter
@Alias("DailyFindVo")
public class DailyFindVo {
    private int userNo;
    private LocalDate todoDate;
}
