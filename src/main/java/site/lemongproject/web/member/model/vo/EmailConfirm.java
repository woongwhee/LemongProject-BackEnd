package site.lemongproject.web.member.model.vo;

import java.sql.Date;

public class EmailConfirm {

    private int confirmNo; // 인증번호

    private String code; // 인증코드

    private String email; // 이메일

    private Date confirmDate; // 인증번호 발급 시간
    
    private int status; // 인증 처리 여부

}
