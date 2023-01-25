package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.vo.TemplateCreateVo;
import site.lemongproject.web.template.model.vo.TemplateUpdateVo;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateDao implements TemplateDao {
    final private SqlSession sqlSession;
    @Override
    public Template findUnSave(int userNo) {
        return sqlSession.selectOne("templateMapper.findUnSave",userNo);
    }
    @Override
    public List<Template> findList(int categoryNo, int page, int limit) {
        RowBounds rowBounds=new RowBounds(limit,page*limit+1);
        if(categoryNo==0){
            sqlSession.selectList("templateMapper.findUnSave",rowBounds);
        }
        return sqlSession.selectList("templateMapper.findUnSave",categoryNo,rowBounds);
    }
    @Override
    public int countTemplate(int categoryNo) {
        if(categoryNo==0){
            return sqlSession.selectOne("templateMapper.countTemplate");
        }
        return sqlSession.selectOne("templateMapper.countTemplate",categoryNo);
    }
    @Override
    public int deleteUnSave(int userNo){
        return sqlSession.delete("templateMapper.deleteUnSave",userNo);
    }
    @Override
    public int deleteTemp( int templateNo){
        return sqlSession.delete("templateMapper.deleteTemp",templateNo);
    }
    @Override
    public int createTemp(int userNo) {
        return sqlSession.insert("templateMapper.createTemp", userNo);
    }

    @Override
    public int uploadTemp(int userNo) {
        return sqlSession.update("templateMapper.upload",userNo);
    }
    @Override
    public int updateUnSave(TemplateUpdateVo templateVo){
        return sqlSession.update("templateMapper.updateUnSave",templateVo);
    }


}
