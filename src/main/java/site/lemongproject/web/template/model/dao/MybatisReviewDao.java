package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.vo.ReviewDeleteVo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisReviewDao implements ReviewDao{
    final private SqlSession session;
    @Override
    public int insertOne(ReviewInsertVo riv) {
        return session.insert("reviewMapper.insertOne",riv);
    }
    @Override
    public Review findOne(int reviewNo) {
        return session.selectOne("reviewMapper.findOne",reviewNo);
    }
    @Override
    public List<Review> findByTp(int templateNo) {
        return session.selectList("reviewMapper.findByTp",templateNo);
    }
    @Override
    public int deleteOne(ReviewDeleteVo reviewDeleteVo) {
        return session.delete("reviewMapper.deleteOne",reviewDeleteVo);
    }
    @Override
    public int deleteByTemplate(int templateNo) {
        return session.delete("reviewMapper.deleteByTemplate",templateNo);
    }

}
