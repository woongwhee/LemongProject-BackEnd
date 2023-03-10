package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.vo.*;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateDao implements TemplateDao {
    final private SqlSession sqlSession;
    @Override
    public TPUnsaveVo findUnSave(int userNo) {
        return sqlSession.selectOne("templateMapper.findUnSave",userNo);
    }
    @Override
    public List<Template> findList(TemplateFindVo findVo, int limit) {
        int offSet=findVo.getPage()*limit;
        RowBounds rowBounds=new RowBounds(offSet,limit);
        return sqlSession.selectList("templateMapper.findMany",findVo,rowBounds);
    }

    @Override
    public TemplateDetailVo findDetail(TemplateFindVo findVo) {
        return sqlSession.selectOne("templateMapper.findDetail",findVo);
    }
    @Override
    public Template findOne(int templateNo) {
        return sqlSession.selectOne("templateMapper.findOne",templateNo);
    }
    @Override
    public int countTemplate(int categoryNo) {return sqlSession.selectOne("templateMapper.countTemplate",categoryNo);}
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
    public int uploadUnSave(int userNo) {
        return sqlSession.update("templateMapper.uploadUnSave",userNo);
    }
    @Override
    public int updateUnSave(TemplateUpdateVo templateVo){
        return sqlSession.update("templateMapper.updateUnSave",templateVo);
    }
    @Override
    public boolean isWriter(WriterCheckVo writerCheckVo){
        return sqlSession.selectOne("templateMapper.isWriter",writerCheckVo);
    }

    @Override
    public int findRange(int templateNo) {
        return sqlSession.selectOne("templateMapper.findRange",templateNo);
    }

    @Override
    public List<TemplateListVo> findByUser(int userNo) {
        return sqlSession.selectList("templateMapper.findByUser",userNo);
    }

}
