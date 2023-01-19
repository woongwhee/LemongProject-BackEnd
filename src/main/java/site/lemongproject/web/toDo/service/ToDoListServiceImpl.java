package site.lemongproject.web.toDo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lemongproject.web.toDo.model.dao.ToDoListDao;
import site.lemongproject.web.toDo.model.vo.ToDo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService{

    final private ToDoListDao todoDao;

    public List<ToDo> selectToDo(){
        return todoDao.selectToDo();
    }
}
