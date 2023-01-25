package site.lemongproject.web.todo.service;

import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> selectToDo();

    int insertTodo(Todo t);

    int updateTodo();
}
