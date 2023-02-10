package site.lemongproject.web.member.model.dao;


import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.List;

public interface ProfileDao {
    int checkNick(String nick);
    int insertProfile(Profile profile);

    Profile findOne(int userNo);

    int createProfile(String nickName);

    int updateProfile(Profile profile);
    int updateProfilePhoto(Photo p);

    int deleteProfile(int userNo);

    List<Profile> searchUser(String userNick);

    Profile MyPageNickCheck(String checkNick);

    Profile updateMyNick(Profile pro);

    Profile updateMyContent(Profile p);
}
