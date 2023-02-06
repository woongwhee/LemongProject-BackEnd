package site.lemongproject.web.todo.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TodoDao {

    final private SqlSession sqlSession;

    public List<Todo> selectTodo(Todo t) {
        return sqlSession.selectList("todoMapper.selectTodo", t);
    }

    public int insertTodo(Todo t){
        return  sqlSession.insert("todoMapper.insertTodo",t);
    }

    public int deleteTodo(Todo t) {
        return sqlSession.update("todoMapper.deleteTodo", t);
    }

    public int clearTodo(Todo t) {
        return sqlSession.update("todoMapper.clearTodo", t);
    }

    public int updateTodo(Todo t){
        return sqlSession.update("todoMapper.updateTodo", t);
    }

    public int delayTodo(Todo t){ return sqlSession.update("todoMapper.delayTodo", t);}

    public List<Todo> calTodo(Todo t){
        return sqlSession.selectList("todoMapper.calTodo", t);
    }

    public int dndTodo(Todo t){ return  sqlSession.update("todoMapping.dndTodo", t);}

}
