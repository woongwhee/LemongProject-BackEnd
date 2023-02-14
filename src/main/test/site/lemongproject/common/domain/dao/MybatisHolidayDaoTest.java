package site.lemongproject.common.domain.dao;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.lemongproject.config.Configure;
import site.lemongproject.web.todo.model.dto.PeriodVo;
import site.lemongproject.web.todo.model.vo.OfficialHoliday;
import site.lemongproject.web.todo.model.dao.HolidayDao;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisHolidayDaoTest extends Configure {
    @Autowired
    HolidayDao holidayDao;

    @Test
    public void findByMonth() throws ParseException {
        int year=2022;
        int month=1;
        LocalDate localMonth= LocalDate.of(year,month,1);
        Map<String,LocalDate> map=new HashMap<>();
//        map.put("month",localMonth);
        List<OfficialHoliday> holidays = holidayDao.findByMonth(localMonth);

        System.out.println(holidays);
        Assertions.assertThat(holidays).isNotNull();
    }

    @Test
    public void findByPeriod() {
        LocalDate startDate= LocalDate.parse("2023-01-01");
        int todoCount=30;
        int weekDo=4;
        long range=(todoCount/weekDo+1)*7;
        System.out.println("range"+range);
        LocalDate endDate=startDate.plusDays(range);
        PeriodVo periodVo=new PeriodVo(startDate,endDate);
        List<OfficialHoliday> holidays = holidayDao.findByPeriod(periodVo);
        System.out.println(holidays);
        Assertions.assertThat(holidays).isNotNull();

    }
    @Test
    public void LocalDateTest(){
        LocalDate startDate=LocalDate.parse("2022-01-02");
        System.out.println(startDate.getDayOfWeek());
        System.out.println(startDate.getDayOfWeek().getValue()-1);
    }
}