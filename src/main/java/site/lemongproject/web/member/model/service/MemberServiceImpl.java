package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;

    public Member loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        return loginUser;
    }

    @Override
    public List<Profile> selectMyProList() {
        return memberDao.selectMyProList();
    }

//    public Profile selectMyProList(int userNo){
//        return memberDao.selectProfile(userNo);
//    }

    public List<Member> selectUser(){
        return memberDao.selectUser();
    }

    public int updateUser(String nickName){
        return memberDao.updateUser(nickName);
    }

    @Override
    public int checkNickName(String nickName) {
        return 0;
    }

    public int updateComment(String comment){
        return memberDao.updateComment(comment);
    }

    @Override
    public int insertUserProfile(Photo p) {
        return memberDao.insertUserProfile(p);
    }

    public int myupdatePwd(String upPwd){
        return memberDao.myupdatePwd(upPwd);
    }

    public List<Photo> selectMyProfile(){
        return memberDao.selectMyProfile();
    }

    public int updateUserProfile(Photo p){
        return memberDao.updateUserProfile(p);
    }

    public int deleteUser(){
        return memberDao.deleteUser();
    }

}
