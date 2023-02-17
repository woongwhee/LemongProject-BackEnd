package site.lemongproject.web.template.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Date;
@Getter
@Setter
@ToString
@Alias("Review")
public class Review {
    int reviewNo;
    int templateNo;
    String reviewDetail;
    Profile userProfile;
    Date reviewAt;
}
