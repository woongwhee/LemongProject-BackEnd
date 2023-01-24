package site.lemongproject.web.member.model.service;

import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

public interface MemberService {

    public Member loginMember(Member m);


    // 유저 프로필 테이블에서 가져오기
    public List<Profile> selectMyProList();

    // 멤버 테이블에서 가져오기
    public List<Member> selectUser();

    int updateUser(String nickName);
    int checkNickName(String nickName);
    public int updateComment(String comment);

    public int insertUserProfile(Photo p);

    public int myupdatePwd(String upPwd);

    public List<Photo> selectMyProfile();

    public int updateUserProfile(Photo p);

    public int deleteUser();
}
