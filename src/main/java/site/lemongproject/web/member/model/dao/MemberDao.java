package site.lemongproject.web.member.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.Member;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    final private SqlSession sqlSession;
    public Member loginMember(Member m) {
       return sqlSession.selectOne("memberMapper.loginMember",m );
    }


    public int insertMember(Map<String, Object> m) {
        return sqlSession.insert("memberMapper.insertMember", m);
    }


    public int checkNick(Member m) {
        return sqlSession.selectOne("memberMapper.checkNick", m);
    }

    public int checkEmail(Member m) {
        return 1;
    }

}
