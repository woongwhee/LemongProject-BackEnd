package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;
import site.lemongproject.web.template.model.vo.WriterCheckVo;

import java.util.List;

public interface TemplateTodoDao {
    List<TemplateTodo> findByTemplate(int templateNo);
    TemplateTodo findOne(long tpTodoNo);

    int insertOne(TemplateTodo templateTodo);

    int updateOne(TemplateTodo templateTodo);
    //삭제후 value조정
    int afterDelete(TemplateTodo templateTodo);
    int deleteOne(TPTodoDeleteVo tdv);
    int deleteUnSave(int userNo);
    int deleteTemplate(long templateNo);
    boolean isWriter(WriterCheckVo writerCheckVo);

    int deleteRangeOver(TemplateUpdateVo templateVo);
}
