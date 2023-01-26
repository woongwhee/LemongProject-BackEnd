package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.MybatisProfileDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Connection;
import java.util.Map;

@Service
@Transactional
//@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    final private MybatisProfileDao profileDao;


    public MemberServiceImpl(MemberDao memberDao, MybatisProfileDao profileDao) {
        this.memberDao = memberDao;
        this.profileDao = profileDao;
    }

    public Member loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        System.out.println("서비스 : " + loginUser);
        return loginUser;
    }


    public int insertMember(Map<String, Object> m) {
        int result1 = memberDao.insertMember(m);
        int result2 = profileDao.insertNick(m);
        System.out.println(result1);
        System.out.println(result2);

        int result = result1 * result2;

        System.out.println("회원가입 dao 실행 : " + result);
        return result;
    }


    public int checkNick(Member m) {
        int result = memberDao.checkNick(m);
        System.out.println("중복 체크 dao 실행: "+result);
        return result;
    }


    public int checkEmail(Member m) {
        int result = memberDao.checkEmail(m);
        System.out.println("인증 번호 dao 실행: "+result);
        return result;
    }


}
