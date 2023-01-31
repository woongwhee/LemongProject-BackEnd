package site.lemongproject.web.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.todo.model.dao.TodoDao;
import site.lemongproject.web.todo.model.vo.Todo;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    final private TodoDao todoDao;

    public List<Todo> selectTodo(Todo t){
        return todoDao.selectTodo(t);
    }

    public int insertTodo(Todo t){

        return todoDao.insertTodo(t);
    }

    public int deleteTodo(Todo t) {

        return todoDao.deleteTodo(t);
    }

    public int clearTodo(Todo t) {

        return todoDao.clearTodo(t);
    }

    public int updateTodo(Todo t){

        return todoDao.updateTodo(t);
    }

    public int delayTodo(Todo t){
        return todoDao.delayTodo(t);
    }



}
