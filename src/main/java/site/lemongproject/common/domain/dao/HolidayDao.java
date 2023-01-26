package site.lemongproject.common.domain.dao;

import site.lemongproject.common.domain.dto.OfficialHoliday;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface HolidayDao {
     int insertMany(List<OfficialHoliday> holidays);
     List<OfficialHoliday> findByMonth(Date month);
     List<OfficialHoliday> findByPeriod(Map<String, Date> periodMap);
}
