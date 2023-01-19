package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateTodoDao implements TemplateTodoDao{
    final private SqlSession session;
    @Override
    public List<TemplateTodo> findMany(long templateNo){
        return session.selectList("templateTodoMapper.findMany",templateNo);
    }
    @Override
    public int insertOne(TemplateTodo templateTodo){
        return session.insert("templateTodoMapper.insertOne",templateTodo);
    }

    @Override
    public int deleteOne(long templateTodoNo) {
        return session.delete("templateTodoMapper.deleteOne");
    }
    @Override
    public int deleteTemplate(long templateNo){
        return session.delete("templateTodoMapper.deleteTemplate");
    }
    @Override
    public int updateOne(TemplateTodo templateTodo){
        return session.update("templateTodoMapper.update",templateTodo);
    }
}
