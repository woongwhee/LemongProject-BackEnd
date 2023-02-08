package site.lemongproject.web.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Alias("PeriodVo")
public class PeriodVo {
    LocalDate startDate;
    LocalDate endDate;
}
