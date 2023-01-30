package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

public interface ReviewDao {
    int insertOne(ReviewInsertVo reviewInsertVo);
    int deleteOne(int reviewNo);
    int deleteByTemplate(int templateNo);

}
