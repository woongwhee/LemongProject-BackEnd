package site.lemongproject.web.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.MailUtil;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("p")
@RequiredArgsConstructor
public class PublicController {

    final private MemberService memberService;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private MailUtil mailUtil;


    // 로그인
    @PostMapping("login")
    public ResponseBody<Member> loginMember(@RequestBody Member m, HttpSession session) {
        /*  @RequestBody(클라이언트-서버) -> json 객체를 자바 객체로 변환해줌
            HTTP 요청의 바디 내용을 통째로 자바객체로 변환해서 매핑된 메소드 파라미터로 전달

            @ResponseBody(서버-클라이언트) -> 자바객체를 HTTP요청의 바디내용으로 매핑하여 클라이언트에게 전송
            이 어노테이션이 붙어있다면 HTTP 요청의 미디어 타입과 파라미터의 타입을 먼저 확인해야 한다.
            ex) text/html;charset=UTF-8

            현재 데이터를 POST 방식으로 요청하고 있는데, 이 때문에 RequestBody를 사용해야 한다.
            GET 방식은 파라미터를 직접 담아서 요청하는 방식이기 때문에 requestbody에 담지 않는다.

            때문에 왜 프론트(리액트) 서버에서는 json 형태로 값을 넘겨줬는데 스프링(자바) 서버에서는 받지 못했는가?
            post 방법으로 스프링에서 받고 있기 때문에 json 형태로 받은 데이터를
            자동으로 자바객체로 변환하지 못했기 때문에 모든 데이터가 null 값으로 출력된 것으로 보인다.

            참고 사이트) https://annajin.tistory.com/107
        */
        Member loginUser = memberService.loginMember(m);

        // 암호화 후
        if(loginUser != null && bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
            System.out.println("컨트롤러 넘어옴");
            System.out.println(ResponseBuilder.success(loginUser));
            return ResponseBuilder.success(loginUser);
        } else {
            System.out.println("컨트롤러 못 넘어옴");
            return ResponseBuilder.unLogin(null);
        }

    }


    // 회원가입
    // 테스팅
    @PostMapping("join")
    public ResponseBody<Map<String, Object>> insertMember(@RequestBody Map<String, Object> m) {
        System.out.println(m);
        String originPwd = String.valueOf(m.get("userPwd"));
        System.out.println("암호화 전 비밀번호 : " + originPwd);

        int result = memberService.insertMember(m);
        System.out.println(result);

        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.unJoin(result);
        }

    }


    // 닉네임 체크
//    @PostMapping("join/chNick")
//    public ResponseBody<Member> checkNick(@RequestBody Member m) {
//        int result = memberService.checkNick(m);
//        System.out.println(m.getNickName());
//        System.out.println(result);
//        if(result > 0) {
//            return ResponseBuilder.unAbleNic(result);
//        } else {
//            return ResponseBuilder.success(result);
//        }
//    }



    // 이메일 전송
//    @PostMapping("join/chEmail")
//    public ResponseBody<Member> checkEmail(@RequestBody Member m) {
//
//        Random random = new Random();
//        int createNum = 0; // 1자리 난수
//        String ranNum = ""; // 1자리 난수 형변환 변수
//        int letter = 6; // 6자리 난수
//        String resultNum = "";
//
//        for(int i=0; i<letter; i++) {
//            createNum = random.nextInt(9);
//            ranNum = Integer.toString(createNum);
//            resultNum += ranNum;
//        }
//
//        String email = m.getEmail();
//        String subject = "[LEMONG] 회원가입 인증번호 입니다.";
//        String message = "회원가입을 위하여 아래의 인증 번호를 입력해주세요. \n\n" + resultNum;
//
//        MailMessage mail = new MailMessage();
//        mail.setEmail(email);
//        mail.setSubject(subject);
//        mail.setMessage(message);
//
//        mailUtil.send(mail);
//        // send가 됐는지... 어떻게 체크해...? ? ? ? ? ? ? ?
//        // ?-?
//        return ResponseBuilder.success(m);
//    }


}
