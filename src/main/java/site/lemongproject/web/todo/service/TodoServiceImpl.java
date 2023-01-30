package site.lemongproject.web.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.todo.model.dao.TodoDao;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    final private TodoDao todoDao;

    public List<Todo> selectToDo(){

        return todoDao.selectToDo();
    }

    public int insertTodo(Todo t, List<Todo> tdl){

        return todoDao.insertTodo(t, tdl);
    }

    public int updateTodo(){

        return todoDao.updateTodo();
    }



}
