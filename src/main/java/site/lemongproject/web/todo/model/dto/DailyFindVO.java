package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
@Getter
@Setter
public class DailyFindVO {
    private LocalDate todoDate;
    private int userNo;
}
