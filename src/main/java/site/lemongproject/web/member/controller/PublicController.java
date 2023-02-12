package site.lemongproject.web.member.controller;


import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.domain.dto.MailMessage;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.type.SocialType;
import site.lemongproject.common.util.MailUtil;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.vo.KakaoToken;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("p")
@RequiredArgsConstructor
public class PublicController {

    final private MemberService memberService;
    final private MailUtil mailUtil;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인
    @PostMapping("/login")
    public ResponseBody<Member> loginMember(@RequestBody Member m, HttpSession session) {
        System.out.println(m);
        m.setSocialType(SocialType.NONE);
        Profile loginUser = memberService.loginMember(m);
        // 암호화 후
        if(loginUser != null) {
            System.out.println("컨트롤러 넘어옴");
            session.setAttribute("loginUser",loginUser);
            session.setAttribute("socialType",SocialType.NONE);
//            System.out.println(ResponseBuilder.success(loginUser));
            return ResponseBuilder.success(loginUser);
        } else {
            System.out.println("컨트롤러 못 넘어옴");
            return ResponseBuilder.unLogin(null);

        }

    }


    // 회원가입
    @PostMapping("join")
    public ResponseBody<Map<String, Object>> insertMember(@RequestBody JoinVo joinVo) {

        // 비밀번호 암호화
        String encPwd = bCryptPasswordEncoder.encode(joinVo.getUserPwd());
        joinVo.setUserPwd(encPwd);
        joinVo.setSocialType(SocialType.NONE);
        // 암호화된 비밀번호 setting 해주기
        int result = memberService.insertMember(joinVo);

        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.unJoin(result);
        }

    }


    // 닉네임 체크
    @PostMapping("join/chNick")
    public ResponseBody<Integer> checkNick(@RequestBody Map<String, Object> nick) {
        String nickName = String.valueOf(nick.get("nickName"));
        int result = memberService.checkNick(nickName);
        System.out.println(result);
        if(result > 0) {
            return ResponseBuilder.hasSameNick(result);
        } else {
            return ResponseBuilder.success(result);
        }
    }



    // 이메일 전송
    @PostMapping("join/chEmail")
    public ResponseBody<Map<String, Object>> sendEmail(@RequestBody Map<String,Object> e) {
        String email = String.valueOf(e.get("email"));
        System.out.println(email);

        // 이메일 중복 체크
        int exEmail = memberService.isExEmail(email);
        System.out.println(exEmail);

        if(exEmail > 0) { // 중복 이메일 존재
            return ResponseBuilder.isExEmail(exEmail);

        } else { // 중복된 이메일 x
            // 랜덤 값 생성
            String ranNum = mailUtil.ranNum();
            // 보낼 값 기본값 셋팅
            MailMessage mailMessage = mailUtil.setConfirmMail(email, ranNum);
            // MaillUtil 초기화(메일전송)
            mailUtil.htmlSend(mailMessage); // html 형식으로(테스트 중)

            // 체크를 위한 코드
            EmailConfirm confirm = new EmailConfirm();
            confirm.setEmail(email);
            confirm.setCode(ranNum);

            int authCode = memberService.insertConfirm(confirm);

            System.out.println("이게 뜬다면 error는 안났다.");

            if(authCode > 0) {
                return ResponseBuilder.success(authCode);
            } else {
                return ResponseBuilder.failEmail(authCode);
            }

        }

    }

    // 인증번호 체크
    @PostMapping("join/chEmailNum")
    public ResponseBody<Map<String, Object>> checkEmailNum(@RequestBody Map<String, Object> auth){
        String email = String.valueOf(auth.get("email")); // 입력한 이메일
        String code = String.valueOf(auth.get("emailNum")); // 사용자가 입력한 인증코드

        EmailConfirm confirm = new EmailConfirm();
        confirm.setEmail(email);
        confirm.setCode(code);

        int result = memberService.checkEmail(confirm);
        System.out.println(confirm);
        System.out.println("authoCode: "+result);

        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.failAuthEmail(result);
        }
    }
    @GetMapping("checkLogin")
    public ResponseBody<Profile> checkLogin(@SessionAttribute(value = "loginUser",required = false) Profile loginUser){

        if(loginUser==null){
            return ResponseBuilder.unLogin(null);
        }else {
            return ResponseBuilder.success(loginUser);
        }

    }


    // 카카오 로그인
    @RequestMapping(value = "kakaoLogin", method = RequestMethod.GET)
    public ResponseBody<Map<String, Object>> kakaoLogin(@RequestParam Map<String, Object> code, HttpSession session) {
        String authCode = String.valueOf(code.get("code"));
        System.out.println("인가코드: "+authCode);

        // 인가코드를 통해 access_token 발급
        String token = memberService.getAccessToken(authCode);
        System.out.println("acessToken: "+token);
        session.setAttribute("accessToken",token);
        // 접속자 정보 얻어오기
        Map<String, Object> kakaoUser = memberService.getKakaoUser(token);
        System.out.println("카카오 유저 정보: "+kakaoUser);

        // 이메일 + 소셜 타입으로 사용자 정보 확인
        String email = String.valueOf(kakaoUser.get("email"));
        String userName = String.valueOf(kakaoUser.get("nickName"));
        SocialType socialType = SocialType.KAKAO;

        // 멤버 셋팅
        Member isKakao = new Member();
        isKakao.setEmail(email);
        isKakao.setSocialType(socialType);
        isKakao.setUserName(userName);

        // 일치하는 회원이 있는지 확인
        Member result = memberService.isSocialUser(isKakao);

        if(result != null) { // 회원정보가 있는 경우 -> 로그인
            Profile oldKakao = memberService.socialProfile(isKakao);
//            isKakao = memberService.isSocialUser(isKakao);
            session.setAttribute("loginUser", oldKakao);
            session.setAttribute("socialType",SocialType.KAKAO);
//            System.out.println(isKakao);
            return ResponseBuilder.success(oldKakao);
        } else { // 회원정보가 없는 경우 -> 회원가입 -> 닉네임 설정
            int kakaoJoin = memberService.insertSocial(isKakao);
            System.out.println("회원가입 여부: "+kakaoJoin);
            isKakao = memberService.isSocialUser(isKakao);
            return ResponseBuilder.noSocial(isKakao);
        }

    }

    @PostMapping("setNickJoin")
    public ResponseBody<Map<String, Object>> setNick(@RequestBody JoinVo newMem) {

        int result = memberService.setNick(newMem);

        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.unJoin(result);
        }
    }


    // 네이버 로그인
    @RequestMapping(value = "naverLogin", method = RequestMethod.GET)
    public ResponseBody<Map<String, Object>> naverLogin(@RequestParam Map<String, Object> aToken, HttpSession session) {

        String token = String.valueOf(aToken.get("accessToken"));
        System.out.println("네이버 토큰: " + token);

        // 사용자 정보 추출
        Map<String, Object> naverUser = memberService.getNaverUser(token);
//        System.out.println("네이버 유저:" + naverUser);
        String userName = String.valueOf(naverUser.get("userName"));
        String email = String.valueOf(naverUser.get("email"));
        SocialType socialType = SocialType.NAVER;

        // 사용자 정보 셋팅
        Member isNaver = new Member();
        isNaver.setUserName(userName);
        isNaver.setEmail(email);
        isNaver.setSocialType(socialType);

        // 일치하는 회원이 있는지 확인
        Member result = memberService.isSocialUser(isNaver);

        if(result != null) { // 회원정보가 있는 경우 -> 로그인
            Profile oldNaver = memberService.socialProfile(isNaver); // 유저가 있을 경우 프로필 반환
            session.setAttribute("loginUser",oldNaver);
            session.setAttribute("socialType",SocialType.NAVER);
            return ResponseBuilder.success(oldNaver);
        } else { // 회원정보가 없는 경우 -> 회원가입 -> 닉네임 설정
            int naverJoin = memberService.insertSocial(isNaver);
            System.out.println("회원가입 여부: "+naverJoin);
            isNaver = memberService.isSocialUser(isNaver);
            return ResponseBuilder.noSocial(isNaver);
        }
    }


    @PostMapping("findPwd/chEmail")
    public ResponseBody<Map<String, Object>> pwdChEmail (@RequestBody Member userEmail){

        userEmail.setSocialType(SocialType.NONE);
        Member exUser = memberService.pwdChEmail(userEmail);

        if(exUser != null) { // 회원정보가 있는 경우 -> 메일 발송

            String ranNum = mailUtil.ranNum();

            MailMessage mailMessage = mailUtil.setConfirmMail(exUser.getEmail(), ranNum);
            mailUtil.htmlSend(mailMessage);

            EmailConfirm confirm = new EmailConfirm();
            confirm.setEmail(exUser.getEmail());
            confirm.setCode(ranNum);

            int authCode = memberService.insertConfirm(confirm);

            if(authCode > 0) {
                return ResponseBuilder.success(authCode);
            } else {
                return ResponseBuilder.failEmail(authCode);
            }

        } else { // 회원 정보가 없는 경우, 일치하지 않는 경우
            return ResponseBuilder.failEmail(0);
        }

    }







}
