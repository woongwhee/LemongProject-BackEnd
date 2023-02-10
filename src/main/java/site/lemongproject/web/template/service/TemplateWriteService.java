package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TPUnsaveVo;
import site.lemongproject.web.template.model.vo.TemplateTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.List;

public interface TemplateWriteService {
    TPUnsaveVo loadInsertPage(int memberNo);
    int updateUnSaveTemplate(TemplateUpdateVo template);

    List<TemplateTodo> insertTodo(TemplateTodoInsertVo tiv);
    TPUnsaveVo resetUnSave(int i);

    int deleteTodo(TPTodoDeleteVo tdv);

    int uploadUnSave(int userNo);
}
