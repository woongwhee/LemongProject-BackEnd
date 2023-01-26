package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.MybatisProfileDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.member.model.vo.Profile;

import java.util.List;
import java.sql.Connection;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;

    public Member loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        System.out.println("서비스 : " + loginUser);
        return loginUser;
    }



    public int insertMember(Map<String, Object> m) {
        int result = memberDao.insertMember(m);
        System.out.println("회원가입 dao 실행 : " + result);
        return result;
    }


    public int checkNick(Map<String, Object> nick) {
        int result = memberDao.checkNick(nick);
        System.out.println("중복 체크 dao 실행: "+result);
        return result;
    }


    public int checkEmail(Member m) {
        int result = memberDao.checkEmail(m);
        System.out.println("인증 번호 dao 실행: "+result);
        return result;
    }

    @Override
    public List<Profile> selectMyProList() {
        return null;
    }

    @Override
    public List<Member> selectUser() {
        return null;
    }

    @Override
    public int updateUser(String nickName) {
        return 0;
    }

    @Override
    public int checkNickName(String nickName) {
        return 0;
    }

    @Override
    public int updateComment(String comment) {
        return 0;
    }

    @Override
    public int insertUserProfile(Photo p) {
        return 0;
    }

    @Override
    public int myupdatePwd(String upPwd) {
        return 0;
    }

    @Override
    public List<Photo> selectMyProfile() {
        return null;
    }

    @Override
    public int updateUserProfile(Photo p) {
        return 0;
    }

    @Override
    public int deleteUser() {
        return 0;
    }


}
