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

    public Profile selectMyProfile(int userNo) {
        return sqlSession.selectOne("profileMapper.selectMyProfile", userNo);
    }

    public Member isSocialUser(Member isSocial) {
        return sqlSession.selectOne("memberMapper.isSocialUser", isSocial);
    }

    public int insertSocial(Member isSocial) {
        return sqlSession.insert("memberMapper.insertSocial", isSocial);
    }

    public Member pwdChEmail(Member userEmail) {
        return sqlSession.selectOne("memberMapper.pwdChEmail", userEmail);
    }

    public Member findUserNo(String email) {
        return sqlSession.selectOne("memberMapper.loginMember", email);
    }

    public int updateToken(Member isNaver) {
        return sqlSession.update("memberMapper.updateToken", isNaver);
    }

    public String selectAccessToken(int userNo) {
        return sqlSession.selectOne("memberMapper.selectAccessToken", userNo);
    }


    public int deleteNaver(int userNo) {
        return sqlSession.update("memberMapper.deleteNaver", userNo);
    }



    public Member selectMembers(int userNo) {
        return sqlSession.selectOne("memberMapper.selectMember", userNo);
    }
}


