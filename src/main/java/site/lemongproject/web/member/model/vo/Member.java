package site.lemongproject.web.member.model.vo;

import lombok.Data;
import site.lemongproject.common.type.SocialType;
@Data
public class Member {
    private int userNo;
    private String email;
    private String userPwd;
    private SocialType socialType;
    private String accessToken;
}
