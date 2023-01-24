package site.lemongproject.web.member.model.vo;

import lombok.Data;
import site.lemongproject.web.photo.model.vo.Photo;

@Data
public class Profile {
    private int userNo;
    private int photoNo;
    private String nickName;
    private String profileComment;
}
