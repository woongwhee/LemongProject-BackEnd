package site.lemongproject.web.member.model.service;

import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.KakaoToken;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

public interface MemberService {

     Profile loginMember(Member m);

     int insertMember(JoinVo m);

     int checkNick(String nickName);
     int updateProfile(Profile profile);

    int checkEmail(EmailConfirm confirm);

    // 유저 프로필 테이블에서 가져오기
    // 멤버 테이블에서 가져오기

     int insertUserPhoto(Photo p);

     int updatePassword(ChangePwdVo cpw);

     int deleteUser(int userNo);
//     이메일인증
    int insertConfirm(EmailConfirm confirm);

    List<Profile> searchUser(String userNick);

    Profile selectMyProfile(int userNo);

    MyProfileVo getMyProfile(int userNo);

    Member selectMembers(int userNo);

    Profile MyPageNickCheck(String checkNick);

    Profile updateMyNick(Profile pro);

    Profile updateMyContent(Profile p);

//    int checkConfirm(EmailConfirm confirm);

    String getAccessToken(String authCode);

    Map<String, Object> getKakaoUser(String token);

    int setNick(JoinVo joinVo);

    Map<String, Object> getNaverUser(String token);

    Member isSocialUser(Member isSocial);

    int insertSocial(Member isSocial);

    int isExEmail(String email);


}
