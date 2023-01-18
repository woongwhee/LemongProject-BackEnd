//package site.lemongproject.common.scheduler;
//
//import com.google.gson.Gson;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
//import org.springframework.test.context.web.WebAppConfiguration;
//import site.lemongproject.common.scheduler.model.dao.MybatisHolidayDao;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
////@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
//@ComponentScan
//@ContextConfiguration(classes = {ApplicationContext.class},loader = AnnotationConfigWebContextLoader.class)
//public class SchedulerServiceTest {
//
//    @Autowired private SchedulerService schedulerService;
//
//    @Test
//    public void updateHoliday() {
//
//        int result=schedulerService.updateHoliday();
//        Assert.assertEquals(result,1);
//    }
//
//}