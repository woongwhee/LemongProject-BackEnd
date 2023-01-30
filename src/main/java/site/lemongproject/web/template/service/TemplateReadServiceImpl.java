package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.common.domain.dao.HolidayDao;
import site.lemongproject.web.template.model.dao.ReviewDao;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

import java.sql.Date;
import java.util.List;
@RequiredArgsConstructor
@Service
public class TemplateReadServiceImpl implements TemplateReadService{

    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;
    final private HolidayDao holidayDao;
    final private ReviewDao reviewDao;
    @Override
    public List<Template> getTemplateList(int categoryNo, int page) {

        return templateDao.findList(categoryNo,page,8);
    }

    @Override
    public Template getTemplateDetail(int templateNo) {
        return templateDao.findOne(templateNo);
    }

    @Override
    public List<TemplateTodo> getTemplateTodo(int templateNo) {
        return templateTodoDao.findByTemplate(templateNo);
    }

    @Override
    public int insertReview(ReviewInsertVo riv) {
        return reviewDao.insertOne(riv);
    }

    @Override
    public int deleteReview(int reviewNo) {
        return reviewDao.deleteOne(reviewNo);
    }
    @Override
    public int SingleStart(String option, Date StartAt, int userNo) {




        return 0;
    }

    @Override
    public int MultiStart(String option, Date StartAt, int userNo) {




        return 0;
    }



}
