package site.lemongproject.web.photo.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.photo.model.vo.Photo;

@RequiredArgsConstructor
@Repository

public class MybatisPhotoDao implements PhotoDao{
    final private SqlSession session;

    /**
     * 프로필사진을 반환함
     */
    @Override
    public Photo findByUser(int userNo) {
        return session.selectOne("photoMapper.findByUser",userNo);
    }
    /**
     * 피드의 사진을 반환함
     */
    @Override
    public Photo findByFeed(int feedNo) {
        return session.selectOne("photoMapper.findByFeed",feedNo);
    }
    /**
     * 특정사진을 반환함
     */
    @Override
    public Photo findOne(int photoNo) {
        return session.selectOne("photoMapper.findOne",photoNo);
    }

    /**
     * 사진을 삽입함
     */
    @Override
    public int insertPhoto(Photo p) {
        return session.insert("photoMapper.insertPhoto",p);
    }

    /**
     * 특정사진을 삭제하는메서드
     */
    @Override
    public int deletePhoto(int photoNo) {
        return session.delete("photoMapper.deletePhoto",photoNo);
    }
    /**
     * 피드에 있는사진을 전부 삭제하는메서드
     */
    @Override
    public int deleteByUser(int userNo) {
        return session.delete("photoMapper.deleteByUser",userNo);
    }
    /**
     * 프로필사진을 삭제해주는 메서드
     */
    @Override
    public int deleteByFeed(int feedNo) {
        return session.delete("photoMapper.deleteByFeed",feedNo);
    }
}
