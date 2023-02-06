package site.lemongproject.web.member.model.vo;

import lombok.Data;
import site.lemongproject.web.photo.model.vo.Photo;

import java.util.ArrayList;


@Data
public class Profile {
    private int userNo;
    private Photo photo;
    private int photoNo;
    private String nickName;
    private String profileComment;

}
