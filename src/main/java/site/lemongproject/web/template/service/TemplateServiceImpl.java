package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.vo.Template;
import site.lemongproject.web.template.model.vo.TemplateTodo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateServiceImpl implements TemplateService{
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;
    @Override
    public Template loadInsertPage(int userNo) {
        Template template= templateDao.findUnSave(userNo);
        if(template!=null){
            List<TemplateTodo> todoList=templateTodoDao.findMany(template.getTemplateNo());
            template.setTodoList(todoList);
        }
        return template;
    }
}
