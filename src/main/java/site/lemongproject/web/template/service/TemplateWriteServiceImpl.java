package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TempalteTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateCreateVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateWriteServiceImpl implements TemplateWriteService {
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;
    @Override
    public Template loadInsertPage(int userNo) {
        Template template= templateDao.findUnSave(userNo);
        if(template==null){
            templateDao.createTemp(userNo);
            template=templateDao.findUnSave(userNo);
        }
        List<TemplateTodo> todoList=templateTodoDao.findByTemplate(template.getTemplateNo());
        template.setTodoList(todoList);
        return template;
    }

    @Override
    public int updateUnSaveTemplate(TemplateUpdateVo templateVo) {
        return templateDao.updateUnSave(templateVo);
    }

    @Override
    public int saveTemplate(int userNo) {
        return templateDao.uploadTemp(userNo);
    }

    @Override
    public List<TemplateTodo> insertTodo(TempalteTodoInsertVo tiv) {
        List<TemplateTodo> todoList = new ArrayList<>();
        int result=1;
        for (int day : tiv.getDayList()) {
            TemplateTodo t = new TemplateTodo();
            t.setTemplateNo(tiv.getTemplateNo());
            t.setDay(day);
            t.setContent(tiv.getContent());
            todoList.add(t);
            result*=templateTodoDao.insertOne(t);
        }
        return todoList;
    }



    @Override
    public Template resetUnSave(int userNo) {
        int result=templateTodoDao.deleteUnSave(userNo);
        System.out.println(result);
        templateDao.deleteUnSave(userNo);
        templateDao.createTemp(userNo);
        return templateDao.findUnSave(userNo);
    }


    @Override
    public int deleteTemplateTodo(long tpTodoNo) {
        TemplateTodo td=templateTodoDao.findOne(tpTodoNo);
        int result=templateTodoDao.deleteOne(tpTodoNo);
        result*=templateTodoDao.afterDelete(td);
        return result;
    }


}
