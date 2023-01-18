package site.lemongproject.web.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.Member;

@Repository
public class MemberDao {

    public Member loginMember(SqlSession sqlSession, Member m) {
        return sqlSession.selectOne("memberMapper.loginMember", m);
    }

}
