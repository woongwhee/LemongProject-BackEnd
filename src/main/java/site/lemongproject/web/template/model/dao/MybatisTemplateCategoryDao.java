package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.TemplateCategory;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class MybatisTemplateCategoryDao implements TemplateCategoryDao{
    private final SqlSession sqlSession;
    @Override
    public List<TemplateCategory> findList() {
        return sqlSession.selectList("templateCategoryMapper.findList");
    }


}
