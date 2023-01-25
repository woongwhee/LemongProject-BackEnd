package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.util.List;

public interface TemplateTodoDao {
    List<TemplateTodo> findByTemplate(int templateNo);
    TemplateTodo findOne(long tpTodoNo);

    int insertOne(TemplateTodo templateTodo);

    int updateOne(TemplateTodo templateTodo);
    //삭제후 value조정
    int afterDelete(TemplateTodo templateTodo);
    int deleteOne(long templateTodoNo);
    int deleteUnSave(int userNo);
    int deleteTemplate(long templateNo);
}
