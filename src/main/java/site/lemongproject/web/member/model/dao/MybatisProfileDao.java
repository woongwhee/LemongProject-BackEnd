package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.Profile;
@RequiredArgsConstructor
@Repository
public class MybatisProfileDao implements ProfileDao {
    final private SqlSession sqlSession;
    @Override
    public Profile selectProfile(int userNo) {
        return sqlSession.selectOne("profileMapper.findOne",userNo);
    }

}
