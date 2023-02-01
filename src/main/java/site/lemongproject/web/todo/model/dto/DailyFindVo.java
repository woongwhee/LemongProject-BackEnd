package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DailyFindVo {
    private LocalDate todoDate;
    private int userNo;
}
