package site.lemongproject.web.member.model.vo;

import lombok.Data;
import site.lemongproject.web.photo.model.vo.Photo;

@Data
public class Profile {
    private int userNo;
    private Photo photo;
    private String nickName;
    private String profileComment;
}
