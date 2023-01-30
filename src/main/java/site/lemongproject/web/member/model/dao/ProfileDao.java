package site.lemongproject.web.member.model.dao;


import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

public interface ProfileDao {
    int checkNick(String nick);
    int insertProfile(Profile profile);

    Profile findOne(int userNo);


    int createProfile(String nickName);

    int updateProfile(Profile profile);
    int updateProfilePhoto(Photo p);

    int deleteProfile(int userNo);

}
