package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.CGTodoInsertVo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.challenge.model.vo.TodoClearVo;
import site.lemongproject.web.todo.model.dto.DailyFindVo;
import site.lemongproject.web.todo.model.dto.MonthFindVo;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisChallengeTodoDao implements ChallengeTodoDao{
    final private SqlSession session;
    @Override
    public int insertTodoList(CGTodoInsertVo insertVo) {
        return session.insert("challengeTodoMapper.insertTodo",insertVo);
    }
    @Override
    public List<ChallengeTodoVo> findDaily(DailyFindVo dailyFind) {
        return session.selectList("challengeTodoMapper.findDaily",dailyFind);
    }

    @Override
    public int copyTodoList(ChallengeUserVo joinVo) {
        return session.insert("challengeTodoMapper.copyTodo",joinVo);
    }

    @Override
    public int clearChallengeTodo(TodoClearVo clearVo) {
        return session.update("challengeTodoMapper.clearChallengeTodo",clearVo) ;
    }

    @Override
    public List<ChallengeTodo> calChTodo(ChallengeTodo ct){
        return session.selectList("challengeTodoMapper.calChTodo", ct);
    }
    @Override
    public List<Integer> findByCal(MonthFindVo findVo) {
        return session.selectList("challengeTodoMapper.findByCal", findVo);
    }
    @Override
    public ChallengeTodo findOne(long todoNo) {
        return session.selectOne("challengeTodoMapper.findOne",todoNo);
    }

    @Override
    public int deletePlay(ChallengeUserVo userVo) {
        return session.delete("challengeTodoMapper.deletePlay",userVo);
    }


}
