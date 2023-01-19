package site.lemongproject.web.toDo.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.vo.Template;
import site.lemongproject.web.toDo.model.vo.ToDo;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class ToDoListDao {

    final private SqlSession sqlSession;

    public List<ToDo> selectToDo() {
        return sqlSession.selectList("todoMapper.selectTodo");
    }
}
