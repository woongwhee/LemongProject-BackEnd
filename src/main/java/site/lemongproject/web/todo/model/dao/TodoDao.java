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

    public List<Todo> selectToDo() {

        return sqlSession.selectList("todoMapper.selectTodo");
    }

    public int insertTodo(Todo t){
        return  sqlSession.insert("todoMapper.insertTodo");
    }

    public int updateTodo(){
        return sqlSession.update("todoMapper.updateTodo");
    }
}
