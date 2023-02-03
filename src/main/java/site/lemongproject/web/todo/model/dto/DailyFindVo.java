package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DailyFindVo {
    private int userNo;
    private LocalDate todoDate;
}
