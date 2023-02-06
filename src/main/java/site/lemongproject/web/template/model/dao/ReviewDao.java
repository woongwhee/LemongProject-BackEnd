package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.vo.ReviewDeleteVo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

import java.util.List;

public interface ReviewDao {
    int insertOne(ReviewInsertVo reviewInsertVo);

    Review findOne(int reviewNo);

    List<Review> findByTp(int templateNo);

    int deleteOne(ReviewDeleteVo reviewDeleteVo);
    int deleteByTemplate(int templateNo);

}
