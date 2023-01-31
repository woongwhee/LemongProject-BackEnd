package site.lemongproject.web.member.model.service;

import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

public interface MemberService {

     Member loginMember(Member m);

     int insertMember(JoinVo m);

     int checkNick(String nickName);
     int updateProfile(Profile profile);

    int checkEmail(EmailConfirm confirm);

    // 유저 프로필 테이블에서 가져오기
    // 멤버 테이블에서 가져오기

     int insertUserPhoto(Photo p);

     int updatePassword(ChangePwdVo cpw);

     int deleteUser(int userNo);

     // 이메일인증
    int isExEmail(String email);

    int insertConfirm(EmailConfirm confirm);

    List<Profile> searchUser(String userNick);

    Profile selectMyProfile(int userNo);

    Member selectMember(int userNo);

    MyProfileVo getMyProfile(int userNo);


}
