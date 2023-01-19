package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
//      this.sqlSession = sqlSession;
    }

    public Member loginMember(Member m) {

        Member loginUser = memberDao.loginMember(m);


        return loginUser;
    }

    public List<Profile> selectMyProList(){
        return memberDao.selectMyProList();
    }

    public List<Member> selectUser(){
        return memberDao.selectUser();
    }

    public int updateUser(String nickName){
        return memberDao.updateUser(nickName);
    }

    public int updateComment(String comment){
        return memberDao.updateComment(comment);
    }

    public int insertUserProfile(Photo p, String webPath, String serverFolderPath){
        return memberDao.insertUserProfile(p , webPath , serverFolderPath);
    }



}
