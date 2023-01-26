package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

@RequiredArgsConstructor
@Repository
public class MybatisReviewDao implements ReviewDao{
    final private SqlSession session;

    @Override
    public int insertOne(ReviewInsertVo riv) {
        return session.insert("reviewMapper.insertOne",riv);
    }

    @Override
    public int deleteOne(int reviewNo) {
        return session.delete("reviewMapper.deleteOne",reviewNo);
    }

    @Override
    public int deleteByTemplate(int templateNo) {
        return session.delete("reviewMapper.deleteByTemplate",templateNo);
    }

}
