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
       return sqlSession.selectOne("memberMapper.loginMember",m );
    }

    public int insertMember(Member m) {
        return sqlSession.insert("memberMapper.insertMember", m);
    }


    public int checkNick(Member m) {
        return sqlSession.selectOne("memberMapper.checkNick", m);
    }

    public int insertUserProfile(Photo p) {
        return sqlSession.insert("memberMapper.insertUserProfile" , p);
    }

    public List<Photo> selectMyProfile() {
        return sqlSession.selectList("memberMapper.selectMyProfile");
    }

    public int updateUserProfile(Photo p) {
        return sqlSession.update("memberMapper.updateUserProfile" , p);
    }


    public Member findPublic(int userNo) {
        return sqlSession.selectOne("memberMapper.findPublic" , userNo);
    }
}
