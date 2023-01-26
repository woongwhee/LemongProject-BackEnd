package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    final private ProfileDao profileDao;


    public Member loginMember(Member m) {

        Member loginUser = memberDao.loginMember(m);
        System.out.println("서비스 : " + loginUser);
        return loginUser;
    }

    public int insertMember(Member m) {
        int result = memberDao.insertMember(m);
        System.out.println("dao 실행 : " + result);
        return result;
    }

    public int checkNick(Member m) {
        int result = memberDao.checkNick(m);
        System.out.println("dao 실행: "+result);
        return result;
    }

    @Override
    public int insertUserProfile(Photo p) {
        return memberDao.insertUserProfile(p);
    }

    public List<Photo> selectMyProfile(){
        return memberDao.selectMyProfile();
    }

    public int updateUserProfile(Photo p){
        return memberDao.updateUserProfile(p);
    }

    public MyProfileVo getMyProfile(int userNo){
        Member m=memberDao.findPublic(userNo);
        Profile p=profileDao.selectProfile(userNo);
        return new MyProfileVo(m,p);
    }
}
