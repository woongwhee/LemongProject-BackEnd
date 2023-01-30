package site.lemongproject.web.template.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.anotaion.TestRunner;
import site.lemongproject.common.config.ApplicationConfig;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TempalteTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional
//@TestRunner

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,classes = ApplicationConfig.class)

public class TemplateServiceImplTest {
    @Autowired private TemplateWriteService templateService;
    @Autowired private TemplateDao templateDao;
    @Autowired private TemplateTodoDao templateTodoDao;

    @Autowired private ApplicationContext ap;
    @Test
    @DisplayName("리셋테스트")
    public void 리셋(){
        Template t=templateService.resetUnSave(5);
        assertThat(t).isNotNull();
    }
    @Test
    @DisplayName("임시저장템플릿 불러오기 테스트")
    public void loadUnSave(){
        Template t=templateService.loadInsertPage(1);
        assertThat(t).isNotNull();
    }
    @Test
    @DisplayName("템플릿 투두리스트 삽입테스트")
    public void 삽입() {
        Template t=templateService.loadInsertPage(1);
        System.out.println(t);

        TempalteTodoInsertVo ttiv=new TempalteTodoInsertVo();
        ttiv.setTemplateNo(t.getTemplateNo());
        ttiv.setContent("테스트투두");
        List<Integer> day= new ArrayList<>();
        day.add(2);
        day.add(1);
        day.add(3);
        ttiv.setDayList(day);
        ttiv.setUserNo(1);
        int result=templateService.insertTodo(ttiv);
        assertThat(result).isEqualTo(3);

    }
    @Test
    @DisplayName("투두 삭제 테스트")
    public void deleteTodo(){
        int templateNo=30;
        List<TemplateTodo> tdList=templateTodoDao.findByTemplate(templateNo);
        TemplateTodo ttd=tdList.stream().filter(e->e.getValue()==1&&e.getDay()==1).findAny().orElseThrow();
        int result=templateService.deleteTodo(new TPTodoDeleteVo(1,ttd.getTpTodoNo()));
        assertThat(result).isNotZero();
        ttd=tdList.stream().filter(e->e.getValue()==2&&e.getDay()==1).findAny().orElseThrow();
        tdList=templateTodoDao.findByTemplate(templateNo);
        TemplateTodo ttd2=tdList.stream().filter(e->e.getValue()==1&&e.getDay()==1).findAny().orElse(null);
        assertThat(ttd2.getTpTodoNo()).isEqualTo(ttd.getTpTodoNo());
    }
    @Test
    @DisplayName("업데이트 테스트")
    public void 업데이트(){
        Template t=templateService.resetUnSave(1);
        int tno=t.getTemplateNo();
        TemplateUpdateVo tuv=new TemplateUpdateVo();
        tuv.setUserNo(1);
        tuv.setTemplateNo(tno);
        tuv.setTitle("하이");
        int result=templateService.updateUnSaveTemplate(tuv);
        assertThat(result).isNotZero();
        t=templateDao.findUnSave(1);
        assertThat(t.getTitle()).isEqualTo(tuv.getTitle());
//
//        tuv.setCategoryNo(1);
//        tuv.setTitle(null);
//        result=templateService.updateUnSaveTemplate(tuv);
//        assertThat(result).isNotZero();
//        t=templateDao.findUnSave(1);
//        assertThat(t.getTemplateCategory().getCategoryNo()).isEqualTo(tuv.getTitle());
//
//        tuv.setCategoryNo(null);
//        tuv.setContent("내용");
//        result=templateService.updateUnSaveTemplate(tuv);
//        assertThat(result).isNotZero();
//        t=templateDao.findUnSave(1);
//        assertThat(t.getContent()).isEqualTo(tuv.getContent());
//
//        tuv.setContent(null);
//        tuv.setRange(30);
//        result=templateService.updateUnSaveTemplate(tuv);
//        assertThat(result).isNotZero();
//        t=templateDao.findUnSave(1);
//        assertThat(t.getRange()).isEqualTo(tuv.getRange());
//
//

    }
}