package site.lemongproject.web.template.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.config.Configure;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TPUnsaveVo;
import site.lemongproject.web.template.model.vo.TemplateTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@Transactional
public class TpWriteServiceTest extends Configure {
    @Autowired private TemplateWriteService templateService;
    @Autowired private TemplateDao templateDao;
    @Autowired private TemplateTodoDao templateTodoDao;

    @Autowired private ApplicationContext ap;



    @Test
    @DisplayName("리셋테스트")
    public void 리셋(){
        TPUnsaveVo t=templateService.resetUnSave(5);
        assertThat(t).isNotNull();
    }
    @Test
    @DisplayName("임시저장템플릿 불러오기 테스트")
    public void loadUnSave(){
        TPUnsaveVo t=templateService.loadInsertPage(1);
        assertThat(t).isNotNull();
    }
    @Test
    @DisplayName("템플릿 투두리스트 삽입테스트")
    public void 삽입() {
        TPUnsaveVo t=templateService.loadInsertPage(1);
        System.out.println(t);

        TemplateTodoInsertVo ttiv=new TemplateTodoInsertVo();
        ttiv.setTemplateNo(t.getTemplateNo());
//        ttiv.setContent("테스트투두");
        List<Integer> day= new ArrayList<>();
        day.add(2);
        day.add(1);
        day.add(3);
        ttiv.setDayList(day);
        ttiv.setUserNo(1);
        int result = templateService.insertTodo(ttiv);
        assertThat(result).isNotZero();

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
        TPUnsaveVo t=templateService.resetUnSave(1);
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