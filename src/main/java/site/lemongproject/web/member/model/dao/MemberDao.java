package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    final private SqlSession sqlSession;
    public Member loginMember(Member m) {
       return sqlSession.selectOne("memberMapper.loginMember",m );
    }


    public int insertMember(JoinVo joinVo) {
        return sqlSession.insert("memberMapper.insertMember", joinVo);
    }

    public int updatePassword(ChangePwdVo cpw) {
        return sqlSession.update("memberMapper.updatePassword" , cpw);
    }

    public int deleteUser(int userNo) {
        return sqlSession.update("memberMapper.deleteUser",userNo);
    }


//    public int checkEmail(Map<String, Object> e, String ranNum) {
//        e.put("code", ranNum);
//        int eCount = sqlSession.selectOne("memberMapper.checkEmail", e);
//        if(eCount > 0) {
//            sqlSession.delete("memberMapper.deleteEmail", e);
//        }
//        return sqlSession.insert("memberMapper.insertEmail", e);
//    }
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

}
