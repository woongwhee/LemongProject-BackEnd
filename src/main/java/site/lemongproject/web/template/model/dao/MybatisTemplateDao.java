package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.vo.Template;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateDao implements TemplateDao {
    final private SqlSession sqlSession;
    @Override
    public Template findUnSave(int userNo) {
        return null;
    }
}
