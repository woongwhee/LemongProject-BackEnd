package site.lemongproject.web.template.model.dto;

import lombok.Getter;
import lombok.Setter;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Date;
@Getter
@Setter
public class Review {
    int reviewNo;
    int templateNo;
    Profile userProfile;
    int reviewDetail;
    Date reviewAt;


}
