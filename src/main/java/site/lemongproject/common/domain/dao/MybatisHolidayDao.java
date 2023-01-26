package site.lemongproject.common.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.common.domain.dto.OfficialHoliday;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MybatisHolidayDao implements HolidayDao {
    final private SqlSession session;
    @Override
    public int insertMany(List<OfficialHoliday> holidays) {
        int result=0;
        for (OfficialHoliday holiday : holidays) {
        result+=session.insert( "holidayMapper.insertMany",holiday);
        }
        return  result;
    }
    @Override
    public List<OfficialHoliday> findByMonth(Date month){
        return session.selectList("holidayMapper.findByMonth",month);

    }

    @Override
    public List<OfficialHoliday> findByPeriod(Map<String, Date> periodMap){
        return session.selectList("holidayMapper.findByMonth",periodMap);
    }
}
