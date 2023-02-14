package site.lemongproject.web.member.model.vo;

import lombok.Data;
import site.lemongproject.common.type.SocialType;
import site.lemongproject.web.photo.model.vo.Photo;

@Data
public class Member {
    private int userNo; // 회원 번호
    private String email; // 이메일
    private String userPwd; // 비밀번호
    private SocialType socialType; // 소셜 로그인 타입
    private String accessToken; // 소셜 로그인 접근
    private int status; // 회원 상태
    private String userName; // 회원 이름
    private Photo Photo;

}
