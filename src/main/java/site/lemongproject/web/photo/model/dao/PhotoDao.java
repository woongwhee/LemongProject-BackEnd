package site.lemongproject.web.photo.model.dao;

import site.lemongproject.web.photo.model.vo.Photo;

public interface PhotoDao {

    Photo findByUser(int userNo);
    Photo findByFeed(int feedNo);
    Photo findOne(int photoNo);
    int insertPhoto(Photo p);
    int deletePhoto(int photoNo);
    int deleteByUser(int userNo);
    int deleteByFeed(int feedNo);


}
