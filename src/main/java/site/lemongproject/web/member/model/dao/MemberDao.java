package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    final private SqlSession sqlSession;
    public Member loginMember(Member m) {
        return sqlSession.selectOne("memberMapper.loginMember", m);
    }

    public Profile selectProfile(int userNo) {
        return sqlSession.selectOne("memberMapper.selectMyProList",userNo);
    }

    public List<Member> selectUser() {
        return sqlSession.selectList("memberMapper.selectUser");
    }

    public int updateUser(String nickName) {
        return sqlSession.update("memberMapper.updateUser" , nickName);
    }

    public int updateComment(String comment) {
        return sqlSession.update("memberMapper.updateComment" , comment);
    }

    public List<Profile> selectMyProList() {
        return sqlSession.selectList("memberMapper.selectMyProList");
    }

    public int insertUserProfile(Photo p) {
        return sqlSession.insert("memberMapper.insertUserProfile" , p);
    }

    public int myupdatePwd(String upPwd) {
        return sqlSession.update("memberMapper.myupdatePwd" , upPwd);
    }

    public List<Photo> selectMyProfile() {
        return sqlSession.selectList("memberMapper.selectMyProfile");
    }

    public int updateUserProfile(Photo p) {
        return sqlSession.update("memberMapper.updateUserProfile" , p);
    }

    public int deleteUser() {
        return sqlSession.update("memberMapper.deleteUser");
    }
}
