package site.lemongproject.web.member.controller;


import site.lemongproject.common.domain.dto.MailMessage;

import java.util.Map;
import java.util.Random;

public class EmailController {

    // 랜덤 숫자 발생
    public static String ranNum() {

        Random random = new Random();
        int createNum = 0; // 1자리 난수
        String ranNum = ""; // 1자리 난수 형변환 변수
        int letter = 6; // 6자리 난수
        String resultNum = "";

        for(int i=0; i<letter; i++) {
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            resultNum += ranNum;
        }
        return resultNum;
    }


    // 보낼 값 셋팅
    public MailMessage setMail(Map<String, Object> e, String ranNum) {

        String email = String.valueOf(e.get("email"));
        String subject = "[LEMONG] 회원가입 인증번호 입니다.";
        String message = "회원가입을 위하여 아래의 인증 번호를 입력해주세요. \n\n" + ranNum;

        System.out.println(email);

        MailMessage mail = new MailMessage();
        mail.setEmail(email);
        mail.setSubject(subject);
        mail.setMessage(message);

        return mail;
    }


}
