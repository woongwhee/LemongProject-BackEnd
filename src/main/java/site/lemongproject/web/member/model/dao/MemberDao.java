package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    final private SqlSession sqlSession;

    public Member loginMember(Member m) {
        return sqlSession.selectOne("memberMapper.loginMember", m);
    }


    public int insertMember(JoinVo joinVo) {
        return sqlSession.insert("memberMapper.insertMember", joinVo);
    }

    public int updatePassword(ChangePwdVo cpw) {
        return sqlSession.update("memberMapper.updatePassword", cpw);
    }

    public int deleteUser(int userNo) {
        return sqlSession.update("memberMapper.deleteUser", userNo);
    }

    public Member findPublic(int userNo) {
        return sqlSession.selectOne("memberMapper.findPublic", userNo);
    }

    public Member selectMember(int userNo) {
        return sqlSession.selectOne("memberMapper.selectMember", userNo);
    }

    public Profile selectMyProfile(int userNo) {
        return sqlSession.selectOne("profileMapper.selectMyProfile", userNo);
    }

    public int isKakaoUser(Member isKakao) {
        return sqlSession.selectOne("memberMapper.isKakao", isKakao);
    }

    public int insertKakao(Member isKakao) {
        return sqlSession.insert("memberMapper.insertKakao", isKakao);
    }


}

//    public int checkEmail(Map<String, Object> e, String ranNum) {
//        e.put("code", ranNum);
//        int eCount = sqlSession.selectOne("memberMapper.checkEmail", e);
//        if(eCount > 0) {
//            sqlSession.delete("memberMapper.deleteEmail", e);
//        }
//        return sqlSession.insert("memberMapper.insertEmail", e);
//    }
