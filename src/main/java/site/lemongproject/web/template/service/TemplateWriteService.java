package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TempalteTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.List;

public interface TemplateWriteService {
    Template loadInsertPage(int memberNo);
    int updateUnSaveTemplate(TemplateUpdateVo template);

    List<TemplateTodo> insertTodo(TempalteTodoInsertVo tiv);
    Template resetUnSave(int i);

    int deleteTemplate(int userNo,int templateNo);
    int deleteTodo(TPTodoDeleteVo tdv);

    int uploadUnSave(int userNo);
}
