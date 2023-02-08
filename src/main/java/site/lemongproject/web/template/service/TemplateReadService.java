package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.ReviewDeleteVo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;
import site.lemongproject.web.template.model.vo.TemplateFindVo;

import java.sql.Date;
import java.util.List;

public interface TemplateReadService {
    List<Template> getTemplateList(TemplateFindVo findVo);

    int getTemplateCount(int categoryNo);

    Template getTemplateDetail(TemplateFindVo findVo);
    List<TemplateTodo> getTemplateTodo(int templateNo);
    int insertReview(ReviewInsertVo riv);
    int deleteReview(ReviewDeleteVo rdv);

    List<Review> getReviewList(int templateNo);

    int deleteTemplate(int userNo, int templateNo);
    Review getReviewOne(int reviewNo);

    List<TemplateCategory> getTemplateCategory();
}
