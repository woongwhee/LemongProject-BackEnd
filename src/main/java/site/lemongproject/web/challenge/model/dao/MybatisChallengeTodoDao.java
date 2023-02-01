package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.CGTodoInsertVo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.todo.model.dto.DailyFindVO;

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
    public List<ChallengeTodoVo> findDaily(DailyFindVO dailyFind) {
        return session.selectList("challengeTodoMapper.findDaily",dailyFind);
    }

    @Override
    public int copyTodoList(ChallengeUserVo joinVo) {
        return session.insert("challengeTodoMapper.copyTodo",joinVo);
    }


}
