package site.lemongproject.web.member.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class EmailConfirm {
    private int confirmNo; // 인증번호
    private String code; // 인증코드
    private String email; // 이메일
}
