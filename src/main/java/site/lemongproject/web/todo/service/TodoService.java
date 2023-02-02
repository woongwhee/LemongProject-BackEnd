package site.lemongproject.web.todo.service;

import site.lemongproject.web.todo.model.vo.Todo;

import java.util.Date;
import java.util.List;

public interface TodoService {

    List<Todo> selectTodo(Todo t);

    int insertTodo(Todo t);

    int deleteTodo(Todo t);

    int clearTodo(Todo t);

    int updateTodo(Todo t);

    int delayTodo(Todo t);

    List<Todo> calTodo(Todo t);
}
