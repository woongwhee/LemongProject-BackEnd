package site.lemongproject.web.member.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dao.EmailConfirmDao;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.dao.PhotoDao;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    final private ProfileDao profileDao;
    final private PhotoDao photoDao;
    final private EmailConfirmDao confirmDao;
    final private FileUtil fileUtil;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Profile loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        if(bCryptPasswordEncoder.matches(m.getUserPwd(),loginUser.getUserPwd())){
            return profileDao.findOne(loginUser.getUserNo());

        }else{
            return null;
        }
    }


    @Override
//    public int insertMember(Map<String, Object> m) {
    public int insertMember(JoinVo joinVo) {
//        int result = memberDao.insertMember(m);
        int result = memberDao.insertMember(joinVo);
        result *= profileDao.createProfile(joinVo.getNickName());
//        System.out.println("회원가입 dao 실행 : " + result);
//        result*=profileDao.createProfile((String) m.get("nickName"));
        return result;
    }


    @Override
    public int insertConfirm(EmailConfirm confirm) {
        int result = confirmDao.insertConfirm(confirm);
        confirmDao.deleteAnother(confirm);
        return result;
    }

    @Override
    public int checkEmail(EmailConfirm confirm) {
        return confirmDao.checkConfirm(confirm);
    }

//    public int insertMember(Member m) {
//        int result = memberDao.insertMember(m);
//        System.out.println("dao 실행 : " + result);
//        return result;
//    }


    @Override
    public int checkNick(String nickName) {
        int result = profileDao.checkNick(nickName);
        System.out.println("중복 체크 dao 실행: " + result);
        return result;
    }

    @Override
    public int updateProfile(Profile profile) {
        return profileDao.updateProfile(profile);
    }

//
//    public int updateUserProfile(Photo p){
//        return memberDao.updateUserProfile(p);
//    }

    public MyProfileVo getMyProfile(int userNo) {
        Member m = memberDao.findPublic(userNo);
        Profile p = profileDao.findOne(userNo);
        return new MyProfileVo(m, p);
    }

    /**
     * 유저의 프로필사진을 업데이트한다.
     * 이전프로필은 서버에서 삭제된다.
     * @param newP
     * @return
     */
    @Override
    public int insertUserPhoto(Photo newP) {
        Photo oldP = photoDao.findByUser(newP.getUserNo());
        if (oldP != null) {
            fileUtil.deleteFile(oldP);
            photoDao.deletePhoto(oldP.getPhotoNo());
        }
        int result = photoDao.insertPhoto(newP);
        result *= profileDao.updateProfilePhoto(newP);
        return result;
    }

    @Override
    public int updatePassword(ChangePwdVo cpw) {
        cpw.setPassword(bCryptPasswordEncoder.encode(cpw.getPassword()));
        return memberDao.updatePassword(cpw);
    }

    public Member seletMember(int userNo) {
        return memberDao.selectMember(userNo);
    }

    @Override
    public int deleteUser(int userNo) {
        int result = memberDao.deleteUser(userNo);
        result = profileDao.deleteProfile(userNo);
        return result;
    }

    public Profile selectMyProfile(int userNo) {
        return profileDao.findOne(userNo);
    }

    @Override
    public Member selectMember(int userNo) {
        return memberDao.selectMember(userNo);
    }

    public List<Profile> searchUser(String userNick) {
        return profileDao.searchUser(userNick);
    }
}
