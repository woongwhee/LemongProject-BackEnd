package site.lemongproject.web.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.todo.model.dao.TodoDao;
import site.lemongproject.web.todo.model.dto.DailyFindVO;
import site.lemongproject.web.todo.model.dto.DailyTodoVo;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    final private TodoDao todoDao;
    final private ChallengeTodoDao challengeTodoDao;

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

    @Override
    public DailyTodoVo getDaily(DailyFindVO dailyFind) {
        DailyTodoVo todoVo=new DailyTodoVo();
        List<Todo> normalList=todoDao.findDaily(dailyFind);
        List<ChallengeTodoVo>challengeList =challengeTodoDao.findDaily(dailyFind);
        todoVo.setNormalList(normalList);
        todoVo.setChallengeList(challengeList);
        return todoVo;
    }


}
