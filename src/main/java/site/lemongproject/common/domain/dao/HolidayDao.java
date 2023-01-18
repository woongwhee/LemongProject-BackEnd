package site.lemongproject.common.domain.dao;

import site.lemongproject.common.domain.dto.OfficialHoliday;
import java.util.List;

public interface HolidayDao {
     int insertMany(List<OfficialHoliday> holidays);
}
