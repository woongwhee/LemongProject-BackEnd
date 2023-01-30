package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.TPTodoDeleteVo;
import site.lemongproject.web.template.model.vo.TempalteTodoInsertVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;
import site.lemongproject.web.template.model.vo.WriterCheckVo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateWriteServiceImpl implements TemplateWriteService {
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;
    @Override
    public Template loadInsertPage(int userNo) {
        Template template= templateDao.findUnSave(userNo);
        if(template==null){
            templateDao.createTemp(userNo);
            template=templateDao.findUnSave(userNo);
        }
        List<TemplateTodo> todoList=templateTodoDao.findByTemplate(template.getTemplateNo());
        template.setTodoList(todoList);
        return template;
    }

    @Override
    public int updateUnSaveTemplate(TemplateUpdateVo templateVo) {
        return templateDao.updateUnSave(templateVo);
    }

    /**
     * 투두를 입력함
     * @param tiv
     * @return
     */
    @Override
    public int insertTodo(TempalteTodoInsertVo tiv) {
        int result=0;
        boolean isWriter=templateDao.isWriter(new WriterCheckVo(tiv.getUserNo(),tiv.getTemplateNo()));
        if(!isWriter){
            return 0;
        }
        List<TemplateTodo> todoList = new ArrayList<>();
        for (int day : tiv.getDayList()) {
            TemplateTodo t = new TemplateTodo();
            t.setTemplateNo(tiv.getTemplateNo());
            t.setDay(day);
            t.setContent(tiv.getContent());
            todoList.add(t);
            result+=templateTodoDao.insertOne(t);
            System.out.println(t);
        }
        return result;
    }

    /**
     * 임시저장된 템플릿을 초기화함(삭제후 재생성)
     * @param userNo
     * @return
     */
    @Override
    public Template resetUnSave(int userNo) {

        Template t=templateDao.findUnSave(userNo);
        if(t!=null){
            int result=templateTodoDao.deleteUnSave(userNo);
            result*=templateDao.deleteUnSave(userNo);
            if(result==0){
                return null;
            }
        }
        int result=templateDao.createTemp(userNo);
        return templateDao.findUnSave(userNo);
    }

    /**
     * 요청이 작성자인경우 삭제하고 가중치를 제조정함
     * @param tdv
     * @return
     */
    @Override
    public int deleteTodo(TPTodoDeleteVo tdv) {
        boolean isWriter=templateTodoDao.isWriter(new WriterCheckVo(tdv.getUserNo(), (int) tdv.getTpTodoNo()));
        TemplateTodo todo=templateTodoDao.findOne(tdv.getTpTodoNo());
        int result=templateTodoDao.deleteOne(tdv);
        if(result>0){
            result=templateTodoDao.afterDelete(todo);
        }
        return result;
    }


    /**
     * 요청이 작성자일경우 템플릿을 삭제함
     *
     * @param userNo
     * @param templateNo
     * @return
     */
    @Override
    public int deleteTemplate(int userNo,int templateNo) {
        boolean isWriter=templateDao.isWriter(new WriterCheckVo(userNo,templateNo));
        if(!isWriter){
            return 0;
        }
        int result=templateTodoDao.deleteTemplate(templateNo);
        result*=templateDao.deleteTemp(templateNo);
        return result;
    }

    /**
     * 임시저장된 템플릿을저장
     * @param userNo
     * @return
     */
    @Override
    public int uploadUnSave(int userNo) {
        return templateDao.uploadUnSave(userNo);
    }


}
