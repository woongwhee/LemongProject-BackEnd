package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.CGTodoInsertVo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.challenge.model.vo.TodoClearVo;
import site.lemongproject.web.todo.model.dto.DailyFindVo;
import site.lemongproject.web.todo.model.dto.MonthFindVo;

import java.time.LocalDate;
import java.util.List;

public interface ChallengeTodoDao {
    int insertTodoList(CGTodoInsertVo insertVo);
    List<ChallengeTodoVo> findDaily(DailyFindVo dailyFind);
    int copyTodoList(ChallengeUserVo joinVo);

    int clearChallengeTodo(TodoClearVo clearVo);

    List<ChallengeTodo> calChTodo(ChallengeTodo ct);
    List<Integer> findByCal(MonthFindVo findVo);
    ChallengeTodo findOne(long todoNo);

}
