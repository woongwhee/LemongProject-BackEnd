package site.lemongproject.web.template.service;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

import java.sql.Date;
import java.util.List;

public interface TemplateReadService {
    List<Template> getTemplateList(int categoryNo,int page);
    Template getTemplateDetail(int templateNo);
    List<TemplateTodo> getTemplateTodo(int templateNo);
    int insertReview(ReviewInsertVo riv);
    int deleteReview(int reviewNo);
    int SingleStart(String option, Date StartAt,int userNo);
    int MultiStart(String option, Date StartAt,int userNo);
}
