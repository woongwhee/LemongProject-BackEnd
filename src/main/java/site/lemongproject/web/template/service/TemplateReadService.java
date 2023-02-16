package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.*;

import java.util.List;

public interface TemplateReadService {
    List<Template> getTemplateList(TemplateFindVo findVo);

    int getTemplateCount(int categoryNo);

    TemplateDetailVo getTemplateDetail(TemplateFindVo findVo);
    List<TemplateTodo> getTodoByDay(TPDayTodoVo todoVo);
    int insertReview(ReviewInsertVo riv);
    int deleteReview(ReviewDeleteVo rdv);

    List<Review> getReviewList(int templateNo);

    int deleteTemplate(int userNo, int templateNo);
    Review getReviewOne(int reviewNo);

    List<TemplateCategory> getTemplateCategory();
}
