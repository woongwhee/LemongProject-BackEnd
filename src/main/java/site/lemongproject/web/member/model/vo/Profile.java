package site.lemongproject.web.member.model.vo;

import lombok.Getter;
import lombok.Setter;
import site.lemongproject.web.photo.model.vo.Photo;
@Getter
@Setter
public class Profile {
    private int userNo;
    private Photo photo;
    private int photoNo;
    private String nickName;
    private String profileComment;
}
