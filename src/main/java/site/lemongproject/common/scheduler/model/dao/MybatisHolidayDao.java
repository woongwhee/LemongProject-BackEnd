package site.lemongproject.common.scheduler.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.common.scheduler.model.dto.OfficialHoliday;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisHolidayDao implements HolidayDao {
    final private SqlSession session;
    @Override
    public int insertMany(List<OfficialHoliday> holidays) {
        return session.insert( "holidayMapper.insertMany",holidays);
    }
}
