package site.lemongproject.common.domain.dao;

import net.sf.cglib.core.Local;
import site.lemongproject.common.domain.dto.OfficialHoliday;
import site.lemongproject.common.domain.vo.PeriodVo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

public interface HolidayDao {
     int insertMany(List<OfficialHoliday> holidays);
     List<OfficialHoliday> findByMonth(LocalDate month);
     List<OfficialHoliday> findByPeriod(PeriodVo period);

     List<OfficialHoliday> findByMonth(Map<String, LocalDate> map);
}
