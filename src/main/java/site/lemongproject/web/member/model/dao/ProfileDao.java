package site.lemongproject.web.member.model.dao;

import site.lemongproject.web.member.model.vo.Profile;

public interface ProfileDao {
    Profile selectProfile(int userNo);
}
