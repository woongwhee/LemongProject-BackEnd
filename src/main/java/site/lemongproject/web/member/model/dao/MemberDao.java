package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
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

    public List<Profile> selectMyProList() {
        return sqlSession.selectList("memberMapper.selectMyProList");
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

    public int insertUserProfile(Photo p, String webPath, String serverFolderPath) {
        return sqlSession.insert("memberMapper.insertUserProfile" , p);
    }
}
