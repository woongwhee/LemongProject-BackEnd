package site.lemongproject.web.photo.model.dao;

import site.lemongproject.web.photo.model.vo.Photo;

public interface PhotoDao {

    Photo selectByUser(int userNo);
    Photo selectByPNO(int photoNo);
//    List<Photo> selectByFeed(int feed);



}
