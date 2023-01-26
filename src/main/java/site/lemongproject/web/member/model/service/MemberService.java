package site.lemongproject.web.member.model.service;

import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

public interface MemberService {

     Member loginMember(Member m);

     int insertMember(Member m);

     int checkNick(Member m);

    // 유저 프로필 테이블에서 가져오기
    public int insertUserProfile(Photo p);

    public List<Photo> selectMyProfile();

    public int updateUserProfile(Photo p);


    MyProfileVo getMyProfile(int userNo);
}
