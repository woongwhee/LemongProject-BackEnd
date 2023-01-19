package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.util.List;

public interface TemplateTodoDao {
    List<TemplateTodo> findMany(long templateNo);
    int insertOne(TemplateTodo templateTodo);
    int deleteOne(long templateTodoNo);
    int updateOne(TemplateTodo templateTodo);
    int deleteTemplate(long templateNo);
}
