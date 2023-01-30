package site.lemongproject.web.follow.model.vo;

import lombok.Data;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import java.sql.Date;

@Data
public class Follow {

    private int follower;
    private int following;
    private Date followAt;
    private String accept;

    private int count;

    private Photo photo;
    private Profile profile;

}
