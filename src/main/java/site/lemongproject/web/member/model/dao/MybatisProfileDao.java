package site.lemongproject.web.member.model.dao;


import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisProfileDao implements ProfileDao {
    final private SqlSession sqlSession;
    @Override
    public int checkNick(String nickName) {
        return sqlSession.selectOne("profileMapper.checkNick", nickName);
    }

    @Override
    public int insertProfile(Profile profile) {
        return sqlSession.insert("profileMapper.insertProfile",profile);
    }

    @Override
    public Profile findOne(int userNo) {
        return sqlSession.selectOne("profileMapper.findOne",userNo);
    }

    @Override
    public int createProfile(String nickName) {
        return sqlSession.insert("profileMapper.createProfile",nickName);
    }

    @Override
    public int updateProfile(Profile profile) {
        return sqlSession.update("profileMapper.updateProfile",profile);
    }

    @Override
    public int updateProfilePhoto(Photo p) {
        return sqlSession.update("profileMapper.updateProfilePhoto",p);
    }

//    @Override
//    public int deleteProfile(int userNo) {
//        return sqlSession.delete("profileMapper.deleteProfile",userNo);
//    }
    @Override
    public List<Profile> searchUser(String userNick) {
        return sqlSession.selectList("profileMapper.searchUser", userNick);
    }
    @Override
    public Profile MyPageNickCheck(String checkNick){
        return sqlSession.selectOne("profileMapper.myPageNickCheck" , checkNick);
    }

    @Override
    public Profile updateMyNick(Profile pro){
        return sqlSession.selectOne("profileMapper.updateMyNick" , pro);
    }

    @Override
    public Profile updateMyContent(Profile p){
        return sqlSession.selectOne("profileMapper.updateMyContent" , p);
    }

    @Override
    public int deleteUser(int userNo) {
        return sqlSession.delete("profileMapper.deleteUser", userNo);
    }



}
