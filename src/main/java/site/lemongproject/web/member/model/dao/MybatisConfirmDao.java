package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.EmailConfirm;
@Repository
@RequiredArgsConstructor
public class MybatisConfirmDao implements EmailConfirmDao{
    final private SqlSession session;

    @Override
    public int isExEmail(String email) {
        return session.selectOne("emailConfirmMapper.isExEmail", email);
    }



    /**
     *
     * @return 0: 일치하는 코드가 없음 -1:시간초과 1:코드 일치함
     */
    @Override
    public int checkConfirm(EmailConfirm confirm) throws NullPointerException {
        try {
            return session.selectOne("emailConfirmMapper.checkConfirm", confirm);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * 인증코드 삽입
     */
    @Override
    public int insertConfirm(EmailConfirm confirm) {
        return session.insert("emailConfirmMapper.insertConfirm",confirm);
    }

    @Override
    public int deleteAnother(EmailConfirm confirm) {
        return session.delete("emailConfirmMapper.deleteAnother",confirm);
    }

    @Override
    public int deleteEmail() {
        return session.delete("emailConfirmMapper.deleteEmail");
    }

}
