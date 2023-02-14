package site.lemongproject.web.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.web.template.model.vo.*;
import site.lemongproject.web.todo.model.dao.HolidayDao;
import site.lemongproject.web.template.model.dao.ReviewDao;
import site.lemongproject.web.template.model.dao.TemplateCategoryDao;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TemplateReadServiceImpl implements TemplateReadService {

    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;
    final private TemplateCategoryDao templateCategoryDao;
    final private HolidayDao holidayDao;
    final private ReviewDao reviewDao;

    @Override
    public List<Template> getTemplateList(TemplateFindVo findVo) {

        return templateDao.findList(findVo, 8);
    }
    @Override
    public int getTemplateCount(int categoryNo){
     return templateDao.countTemplate(categoryNo);
    }

    @Override
    public Template getTemplateDetail(TemplateFindVo findVo) {
        return templateDao.findDetail(findVo);
    }

    @Override
    public List<TemplateTodo> getTodoByDay(TPDayTodoVo todoVo) {
        return templateTodoDao.findByTemplateDay(todoVo);
    }

    @Override
    public int insertReview(ReviewInsertVo riv) {
        return reviewDao.insertOne(riv);
    }

    @Override
    public int deleteReview(ReviewDeleteVo reviewDeleteVo) {
        return reviewDao.deleteOne(reviewDeleteVo);
    }

    @Override
    public List<Review> getReviewList(int templateNo) {
        return reviewDao.findByTp(templateNo);
    }

    @Override
    public int deleteTemplate(int userNo, int templateNo) {
        boolean isWriter = templateDao.isWriter(new WriterCheckVo(userNo, templateNo));
        if (!isWriter) {
            return 0;
        }
        int result = templateTodoDao.deleteTemplate(templateNo);
        result *= reviewDao.deleteByTemplate(templateNo);
        result *= templateDao.deleteTemp(templateNo);
        return result;
    }

    @Override
    public Review getReviewOne(int reviewNo) {
        return reviewDao.findOne(reviewNo);
    }

    @Override
    public List<TemplateCategory> getTemplateCategory() {
        return templateCategoryDao.findList();
    }


}
