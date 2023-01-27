package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.WriterCheckVo;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateTodoDao implements TemplateTodoDao{
    final private SqlSession session;

    @Override
    public int insertOne(TemplateTodo templateTodo){
        return session.insert("templateTodoMapper.insertOne",templateTodo);
    }
    @Override
    public List<TemplateTodo> findByTemplate(int templateNo){
        return session.selectList("templateTodoMapper.findByTemplate",templateNo);
    }
    @Override
    public TemplateTodo findOne(long tpTodoNo){
        return session.selectOne("templateTodoMapper.findOne",tpTodoNo);
    }
    @Override
    public int deleteOne(TPTodoDeleteVo tdv) {
        return session.delete("templateTodoMapper.deleteOne",tdv);
    }

    @Override
    public int deleteUnSave( int userNo) {
        return session.delete("templateTodoMapper.deleteUnSave", userNo);
    }

    @Override
    public int deleteTemplate(long templateNo){
        return session.delete("templateTodoMapper.deleteTemplate",templateNo);
    }

    @Override
    public boolean isWriter(WriterCheckVo writerCheckVo) {
        return session.selectOne("templateTodoMapper.isWriter",writerCheckVo);
    }

    @Override
    public int updateOne(TemplateTodo templateTodo){
        return session.update("templateTodoMapper.updateOne",templateTodo);
    }
    @Override
    public int afterDelete(TemplateTodo templateTodo){
        return session.update("templateTodoMapper.afterDelete",templateTodo);
    }
}
