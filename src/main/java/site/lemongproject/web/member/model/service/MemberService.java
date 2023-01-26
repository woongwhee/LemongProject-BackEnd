package site.lemongproject.web.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lemongproject.web.member.model.vo.Member;

import java.util.Map;

public interface MemberService {

    public Member loginMember(Member m);

//    public int insertMember(Member m);
    public int insertMember(Map<String, Object> m);

    public int checkNick(Member m);

    public int checkEmail(Member m);

}
