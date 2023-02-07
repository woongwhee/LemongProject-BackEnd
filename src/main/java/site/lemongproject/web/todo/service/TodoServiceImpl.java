package site.lemongproject.web.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.todo.model.dao.TodoDao;
import site.lemongproject.web.todo.model.dto.DailyFindVo;
import site.lemongproject.web.todo.model.dto.DailyTodoVo;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;
import java.util.Map;

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
    public DailyTodoVo getDaily(DailyFindVo dailyFind) {
        DailyTodoVo todoVo=new DailyTodoVo();
        List<Todo> normalList=todoDao.findDaily(dailyFind);
        List<ChallengeTodoVo>challengeList =challengeTodoDao.findDaily(dailyFind);
        System.out.println(challengeList);
        todoVo.setNormalList(normalList);
        todoVo.setChallengeList(challengeList);
        return todoVo;
    }
    public List<Todo> calTodo(Todo t) {
        return todoDao.calTodo(t);
    }

    public int dndTodo(Map<String, Object> todoNo){
        int startValue = todoDao.startValue(todoNo); // 1
        int finishValue = todoDao.finishValue(todoNo); // 2
        int result = 0;
        todoNo.put("startValue",startValue);
        todoNo.put("finishValue",finishValue);
        System.out.println(todoNo);
        result += todoDao.updateStartValue(todoNo);
        result += todoDao.updateFinishValue(todoNo);
        return result;}



}
