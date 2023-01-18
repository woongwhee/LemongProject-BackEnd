package site.lemongproject.common.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.common.domain.dto.OfficialHoliday;

import java.util.List;

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
        System.out.println(result);
        return  result;
    }
}
