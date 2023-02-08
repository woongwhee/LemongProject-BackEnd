package site.lemongproject.web.todo.model.dao;

import site.lemongproject.web.todo.model.dto.MonthFindVo;
import site.lemongproject.web.todo.model.dto.PeriodVo;
import site.lemongproject.web.todo.model.vo.OfficialHoliday;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HolidayDao {
     int insertMany(List<OfficialHoliday> holidays);
     List<OfficialHoliday> findByMonth(LocalDate month);
     List<OfficialHoliday> findByPeriod(PeriodVo period);

     List<OfficialHoliday> findByMonth(MonthFindVo findVo);
}
