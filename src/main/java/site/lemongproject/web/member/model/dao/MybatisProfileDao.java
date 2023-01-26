package site.lemongproject.web.member.model.dao;


import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MybatisProfileDao implements ProfileDao {

    final private SqlSession sqlSession;

    @Override
    public int insertNick(Map<String, Object> m) {
        return sqlSession.insert("", m);
    }
}
