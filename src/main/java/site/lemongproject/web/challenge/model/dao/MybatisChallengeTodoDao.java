package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisChallengeTodoDao implements ChallengeTodoDao{
    final private SqlSession session;
}
