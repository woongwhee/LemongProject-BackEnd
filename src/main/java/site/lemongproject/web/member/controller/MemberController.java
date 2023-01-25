package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;

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
//        System.out.println(request.getParameterNames());
        Member loginUser = memberService.loginMember(m);
        if(loginUser!=null){
            System.out.println("컨트롤러 넘어옴");
            System.out.println(ResponseBuilder.success(loginUser));
            return ResponseBuilder.success(loginUser);
        }else{
            System.out.println("컨트롤러 못 넘어옴");
            return ResponseBuilder.unLogin(null);
        }
    }


    // 회원가입
    @PostMapping("join")
    public ResponseBody<Member> insertMember(@RequestBody Member m, HttpSession session) {
        int result = memberService.insertMember(m);
        System.out.println(m);
        System.out.println(result);
        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            session.setAttribute("loginUser", loginUser);
            mv.setViewName("redirect:/");
        }
    }


    // 닉네임 체크
    @PostMapping("join/chNick")
    public ResponseBody<Member> checkNick(@RequestBody Member m) {
        int result = memberService.checkNick(m);
        System.out.println(m);
        System.out.println(result);
        if(result > 0) {
            return ResponseBuilder.unAbleNic(result);
        } else {
            return ResponseBuilder.success(result);
        }
    }

    // 리액트 <-> 스프링 연동 TEST(성공)
    @GetMapping("/hello")
    public List<String> Hello(){
        return Arrays.asList("Lemong_Project 백엔드 서버 : 8081", "Lemong_Project 프론트 서버 : 3000");
    }

    // 마이페이지 회원정보 수정
    @GetMapping("/selectPro")
    public List<Profile> MyPageUpdate(){

        List<Profile> mList = memberService.selectMyProList();

        return mList;
    }

    // MEMBER테이블 유저 정보 조회.
    @GetMapping("/selectUser")
    public List<Member> selectUser(){
        List<Member> mList = memberService.selectUser();
        ResponseBody<List<Member>> r= ResponseBuilder.success(mList);
        return mList;
    }

    // USER_PROFILE테이블 유저 닉네임 업데이트.
    @GetMapping("/checkNickName")
    public int updateUser(@RequestParam(value="modifyNickname" , required=false) String nickName){
        int upList = memberService.updateUser(nickName);

        System.out.println(nickName);

        return upList;
    }

    // USER_PROFILE테이블 유저 자기소개 업데이트.
    @GetMapping("/updateComment")
    public int updateComment(@RequestParam(value="modifyComment" , required = false) String comment){

        int upList2 = memberService.updateComment(comment);

        System.out.println(comment);

        return upList2;
    }

    @GetMapping("/myPwdUpdate")
    public int myupdatePwd(@RequestParam(value="upPwd" , required = false)String upPwd){
        int result = memberService.myupdatePwd(upPwd);
        return result;
    }

    // 유저 프로필 INSERT. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @RequestMapping(value="/insertUserProfile")
//    @RequestMapping(value="/insertUserProfile", method=RequestMethod.POST)
    public int insertUserProfile(
            @RequestParam(value="file", required=false) MultipartFile[] files){

        String webPath = "/resources/images/userProfile/";

        int result = 0;

        Photo p = new Photo();

        for (MultipartFile mf : files) {

            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈
            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            // 1. 원본 파일명 뽑기.
            String originName= mf.getOriginalFilename();

            // 2. 시간 형식을 문자열로 뽑아오기.
            // 년월일시분초
            String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            // 3. 뒤에 붙을 5자리 랜덤값 뽑기.
            int random = (int)(Math.random() * 90000 + 10000); // 5자리 랜덤값

            // 4. 원본파일명으로부터 확장자명 뽑기.
            // .jpg
            String ext = originName.substring(originName.lastIndexOf("."));



}
