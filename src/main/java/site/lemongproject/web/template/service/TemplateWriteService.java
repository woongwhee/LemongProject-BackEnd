package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TempalteTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.List;

public interface TemplateWriteService {
    int saveTemplate(int userNo);
    Template loadInsertPage(int memberNo);
    int updateUnSaveTemplate(TemplateUpdateVo template);

    List<TemplateTodo> insertTodo(TempalteTodoInsertVo tiv);
    Template resetUnSave(int i);

    int deleteTemplateTodo(long templateNo);
}
