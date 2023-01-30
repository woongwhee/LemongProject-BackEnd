package site.lemongproject.web.member.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.SocialType;
@Getter
@Setter
@Alias("JoinVo")
public class JoinVo {
    private int userNo; // 회원 번호
    private String email; // 이메일
    private String userName; // 회원 이름
    private String userPwd; // 비밀번호
    private String nickName; // 비밀번호
    private SocialType socialType; // 소셜 로그인 타입
}
