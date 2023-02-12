package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.exception.IsNotWriterException;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateWriteServiceImpl implements TemplateWriteService {
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;

    @Override
    public TPUnsaveVo loadInsertPage(int userNo) {
        TPUnsaveVo template = templateDao.findUnSave(userNo);
        if (template != null) {
            List<TemplateTodo> todoList = templateTodoDao.findByTemplate(template.getTemplateNo());
            template.setTodoList(todoList);
            return template;
        }
        templateDao.createTemp(userNo);
        template = templateDao.findUnSave(userNo);
        return template;
    }

    @Override
    public int updateUnSaveTemplate(TemplateUpdateVo templateVo) {
        boolean isWriter = checkTemplateWriter(templateVo.getUserNo(),templateVo.getTemplateNo());
        if(!isWriter){
            throw new IsNotWriterException();
        }
        int result=templateDao.updateUnSave(templateVo);
        System.out.println(result);
        if(result>0&&templateVo.getRange()!=null){
            templateTodoDao.deleteRangeOver(templateVo);
        }
        return result;
    }

    /**
     * 투두를 입력함
     *
     * @param tiv
     * @return
     */
    @Override
    public int insertTodo(TemplateTodoInsertVo tiv) {
        System.out.println(tiv);
        boolean isWriter = checkTemplateWriter(tiv.getUserNo(),tiv.getTemplateNo());
        if (!isWriter) {
            throw new IsNotWriterException("잘못된접근");
        }
        return templateTodoDao.insertMany(tiv);
    }

    /**
     * 임시저장된 템플릿을 초기화함(삭제후 재생성)
     *
     * @param userNo
     * @return
     */
    @Override
    public TPUnsaveVo resetUnSave(int userNo) {

        TPUnsaveVo t = templateDao.findUnSave(userNo);
        if (t != null) {
            int result = templateTodoDao.deleteUnSave(userNo);
            result *= templateDao.deleteUnSave(userNo);
            if (result == 0) {
                return null;
            }
        }
        int result = templateDao.createTemp(userNo);
        return templateDao.findUnSave(userNo);
    }

    /**
     * 요청이 작성자인경우 삭제하고 가중치를 제조정함
     *
     * @param tdv
     * @return
     */
    @Override
    public int deleteTodo(TPTodoDeleteVo tdv) {
        boolean isWriter = checkTodoWriter(tdv.getUserNo(), (int) tdv.getTpTodoNo());
        if (!isWriter) {
            throw new IsNotWriterException();
        }
        TemplateTodo todo = templateTodoDao.findOne(tdv.getTpTodoNo());
        int result = templateTodoDao.deleteOne(tdv);
        if (result > 0) {
            result = templateTodoDao.afterDelete(todo);
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


    /**
     * 임시저장된 템플릿을저장
     *
     * @param userNo
     * @return
     */
    @Override
    public int uploadUnSave(int userNo) {
        return templateDao.uploadUnSave(userNo);
    }
    private boolean checkTemplateWriter(int userNo,int templateNo){
        return templateDao.isWriter(new WriterCheckVo(userNo, templateNo));
    }
    private boolean checkTodoWriter(int userNo,int todoNo){
        return templateTodoDao.isWriter(new WriterCheckVo(userNo, todoNo));
    }


}
