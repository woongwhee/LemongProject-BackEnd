package site.lemongproject.web.template.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.template.model.vo.Template;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MybatisTemplateDao implements TemplateDao {
    final private SqlSession sqlSession;
    @Override
    public Template findUnSave(int userNo) {
        return sqlSession.selectOne("templateMapper.findUnSave",userNo);
    }
    @Override
    public int resetUnSave(int userNo){
        return sqlSession.delete("templateMapper.resetUnSave",userNo);
    }
    @Override
    public int deleteTemp(int templateNo){
        return sqlSession.delete("templateMapper.deleteTemp",templateNo);
    }



    @Override
    public int createTemp(Map<String,Object> createMap) {
        return sqlSession.insert("templateMapper.create",createMap);
    }

    @Override
    public int uploadTemp(int userNo) {
        return sqlSession.update("templateMapper.upload",userNo);

    }
    @Override
    public int updateUnSave(Template template){
        return sqlSession.update("templateMapper.updateUnSave",template);
    }


}
