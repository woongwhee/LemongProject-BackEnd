package site.lemongproject.web.member.model.service;

import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

public interface MemberService {

     Member loginMember(Member m);

     int insertMember(Member m);

     int checkNick(Member m);


    // 유저 프로필 테이블에서 가져오기
     List<Profile> selectMyProList();

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
