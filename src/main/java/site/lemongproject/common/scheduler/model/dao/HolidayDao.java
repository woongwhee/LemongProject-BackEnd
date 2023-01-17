package site.lemongproject.common.scheduler.model.dao;

import site.lemongproject.common.scheduler.model.dto.OfficialHoliday;
import java.util.List;

public interface HolidayDao {
     int insertMany(List<OfficialHoliday> holidays);
}
