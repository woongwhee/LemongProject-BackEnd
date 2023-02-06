package site.lemongproject.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PeriodVo {
    LocalDate startDate;
    LocalDate endDate;
}
