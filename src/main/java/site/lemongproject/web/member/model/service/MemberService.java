package site.lemongproject.web.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {

    public Member loginMember(Member m);


    // 유저 프로필 테이블에서 가져오기
    public List<Profile> selectMyProList();

    // 멤버 테이블에서 가져오기
    public List<Member> selectUser();

    public int updateUser(String nickName);

    public int updateComment(String comment);

    public int insertUserProfile(Photo p, String webPath, String serverFolderPath);
}
