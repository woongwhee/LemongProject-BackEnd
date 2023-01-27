package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dao.EmailConfirmDao;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.dao.PhotoDao;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    final private ProfileDao profileDao;
    final private PhotoDao photoDao;
    final private FileUtil fileUtil;
    final private EmailConfirmDao confirmDao;

    @Override
    public Member loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        System.out.println("서비스 : " + loginUser);
        return loginUser;
    }


    @Override
    public int insertMember(JoinVo joinVo) {
        int result = memberDao.insertMember(joinVo);
        result*=profileDao.createProfile(joinVo.getNickName());
        System.out.println("회원가입 dao 실행 : " + result);
        return result;
    }


    @Override
    public int checkNick(String nickName) {
        int result = profileDao.checkNick(nickName);
        System.out.println("중복 체크 dao 실행: "+result);
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
    public int updateProfile(Profile profile) {
        return profileDao.updateProfile(profile);
    }


    @Override
    public int insertUserPhoto(Photo newP) {
        int result= photoDao.insertPhoto(newP);
        Photo oldP=photoDao.findByUser(newP.getUserNo());
        if(oldP!=null)fileUtil.deleteFile(oldP);
        result*=profileDao.updateProfilePhoto(oldP);
        return result;
    }

    @Override
    public int updatePassword(ChangePwdVo cpw) {
        return memberDao.updatePassword(cpw);
    }

    @Override
    public List<Photo> selectMyProfile() {
        return null;
    }


    @Override
    public int deleteUser(int userNo) {
        int result=memberDao.deleteUser(userNo);
        result=profileDao.deleteProfile(userNo);
        return  result;
    }

    @Override
    public int insertConfirm(EmailConfirm confirm) {
        int result= confirmDao.insertConfirm(confirm);
        confirmDao.deleteAnother(confirm);
        return result;
    }

    @Override
    public int checkConfirm(EmailConfirm confirm) {
        return confirmDao.checkConfirm(confirm);
    }

}
