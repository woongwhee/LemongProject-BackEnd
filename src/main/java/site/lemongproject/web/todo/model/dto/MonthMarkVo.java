package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.response.holidayResponse.Holiday;
import site.lemongproject.web.todo.model.vo.OfficialHoliday;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Alias("MonthMarkVo")
public class MonthMarkVo {
    private List<Integer> challengeDayList;
    private List<Integer> todoDayList;
    private List<OfficialHoliday> holidayList ;
}
