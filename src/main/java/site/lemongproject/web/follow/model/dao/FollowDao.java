package site.lemongproject.web.follow.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.follow.model.vo.Follow;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FollowDao {

    final private SqlSession sqlSession;

    public int insertFollow(Follow f) {
        return sqlSession.insert("followMapper.insertFollow" , f);
    }

    public List<Follow> selectMyFollowerList(Follow f) {
        return sqlSession.selectList("followMapper.selectMyFollowerList" , f);
    }

    public int updateFollowOk(Follow f) {
        return sqlSession.update("followMapper.updateFollowOk" , f);
    }

    public int followDelete(Follow f) {
        return sqlSession.delete("followMapper.followDelete" , f);
    }

    public Follow MyFollowCount(Follow f) {
        return sqlSession.selectOne("followMapper.myFollowCount" , f);
    }

    public Follow AcceptFollowCount(Follow f) {
        return sqlSession.selectOne("followMapper.acceptFollowCount" , f);
    }

    public Follow AcceptFollowingCount(Follow f) {
        return sqlSession.selectOne("followMapper.acceptFollowingCount" , f);
    }

    public List<Follow> selectMyFollowersdList(Follow f) {
        return sqlSession.selectList("followMapper.selectMyFollowersdList" , f);
    }

    public Follow MyFollowingCount(Follow f) {
        return sqlSession.selectOne("followMapper.myFollowingCount" , f);
    }

    public List<Follow> selectAcceptFollowingList(Follow f) {
        return sqlSession.selectList("followMapper.selectAcceptFollowingList" , f);
    }

    public List<Follow> selectAcceptFollowerList(Follow f) {
        return sqlSession.selectList("followMapper.selectAcceptFollowerList" , f);
    }

    public List<Follow> selectMyFollowingList(Follow f) {
        return sqlSession.selectList("followMapper.selectMyFollowingList" , f);
    }

    public List<Follow> selectMyFollowerAlertList(Follow f) {
        return sqlSession.selectList("followMapper.selectMyFollowerAlertList" , f);
    }
}
