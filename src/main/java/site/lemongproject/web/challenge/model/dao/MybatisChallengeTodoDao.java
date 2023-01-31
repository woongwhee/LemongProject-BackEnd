package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisChallengeTodoDao implements ChallengeTodoDao{
    final private SqlSession session;

    @Override
    public int insertTodoList(List<ChallengeTodo> challengeTodoList) {
        int result=1;
        for (ChallengeTodo todo:challengeTodoList) {
            result*=session.insert("challengeTodoMapper.insertTodo",todo);
        }

        return result;
    }
}
