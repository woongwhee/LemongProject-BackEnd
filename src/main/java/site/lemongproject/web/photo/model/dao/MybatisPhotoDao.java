package site.lemongproject.web.photo.model.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.lemongproject.web.photo.model.vo.Photo;

@RequiredArgsConstructor
@Repository

public class MybatisPhotoDao implements PhotoDao{


    @Override
    public Photo selectByUser(int userNo) {
        return null;
    }

    @Override
    public Photo selectByPNO(int photoNo) {
        return null;
    }
}
