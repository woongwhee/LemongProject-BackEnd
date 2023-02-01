package site.lemongproject.common.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.common.domain.dto.OfficialHoliday;
import site.lemongproject.common.domain.vo.PeriodVo;

import java.sql.Date;
import java.time.LocalDate;
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
    public List<OfficialHoliday> findByMonth(LocalDate month){
        return session.selectList("holidayMapper.findByMonth",month);

    }

    @Override
    public List<OfficialHoliday> findByPeriod(PeriodVo period) {
        return session.selectList("holidayMapper.findByPeriod",period);
    }

    @Override
    public List<OfficialHoliday> findByMonth(Map<String, LocalDate> map) {
        return session.selectList("holidayMapper.findByMonth",map);

    }


}
