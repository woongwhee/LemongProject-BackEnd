package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.Email;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.EmailConfirm;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmailDao {

    final private SqlSession sqlSession;

    public int checkEmail(EmailConfirm confirm) {
//        e.put("code", ranNum);
        int eCount = sqlSession.selectOne("emailConfirmMapper.checkEmail", confirm);
        if(eCount > 0) {
            sqlSession.delete("emailConfirmMapper.deleteEmail", confirm);
        }
        return sqlSession.insert("emailConfirmMapper.insertEmail", confirm);
    }


    public int checkEmailNum(EmailConfirm confirm) {
        return sqlSession.selectOne("emailConfirmMapper.checkEmailNum", confirm);
    }





}
