package site.lemongproject.web.member.model.service;

import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

import java.util.Map;

public interface MemberService {

     Member loginMember(Member m);

     int insertMember(Map<String,Object> m);

     int checkNick(Member m);
//    public int insertMember(Member m);

    // 유저 프로필 테이블에서 가져오기
     List<Profile> selectMyProList();
    public int checkEmail(Member m);

    // 멤버 테이블에서 가져오기
     List<Member> selectUser();

    int updateUser(String nickName);
    int checkNickName(String nickName);
     int updateComment(String comment);

     int insertUserProfile(Photo p);

     int myupdatePwd(String upPwd);

     List<Photo> selectMyProfile();
     int updateUserProfile(Photo p);
     int deleteUser();
}
