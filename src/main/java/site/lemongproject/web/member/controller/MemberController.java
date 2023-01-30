package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;
    final private FileUtil fileUtil;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인
    @PostMapping("/login")
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

        // 암호화 전
        Member loginUser = memberService.loginMember(m);
//        if(loginUser!=null){
//            System.out.println("컨트롤러 넘어옴");
//            System.out.println(ResponseBuilder.success(loginUser));
//            return ResponseBuilder.success(loginUser);
//        }else{
//            System.out.println("컨트롤러 못 넘어옴");
//            return ResponseBuilder.unLogin(null);
//        }
//        System.out.println(loginUser);
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
    @PostMapping("join")
    public ResponseBody<Member> insertMember(@RequestBody Member m, HttpSession session) {

        System.out.println("암호화 전 비밀번호 : " + m.getUserPwd());

        // 암호화 작업
        String encPwd = bCryptPasswordEncoder.encode(m.getUserPwd());

        // 암호화된 비밀번호를 Member m에 담아주기
        m.setUserPwd(encPwd);
        System.out.println("암호화 후 비밀번호 : " + m.getUserPwd());

        int result = memberService.insertMember(m);
        System.out.println(m);
        System.out.println(result);

        if(result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.unJoin(result);
        }
    }


    // 닉네임 체크
    @PostMapping("join/chNick")
    public ResponseBody<Member> checkNick(@RequestBody Member m) {
        int result = memberService.checkNick(m);
        System.out.println(m.getNickName());
        System.out.println(result);
        if(result > 0) {
            return ResponseBuilder.unAbleNic(result);
        } else {
            return ResponseBuilder.success(result);
        }
    }

    // 유저 프로필 INSERT. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @RequestMapping(value="/insertUserProfile")
//    @RequestMapping(value="/insertUserProfile", method=RequestMethod.POST)
    public int insertUserProfile(
            @RequestParam(value="file", required=false) MultipartFile[] files ,
            @RequestParam(value="userNo" , required = false) int userNo){

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

            // 5. 다 이어붙이기.
            String changeName = currentTime + random + ext;

            // 폴더에 이미지 저장.
            String savePath = "C:/LemongProject/src/main/webapp/resources/images/userProfile/";

            if (!mf.getOriginalFilename().equals("")) { // 파일명이 비어있지 않은 경우
                try {
                    mf.transferTo(new File(savePath + changeName));
                } catch (IllegalStateException | IOException e) {
                    System.out.println(e.getMessage() + "오류 발생");
                }
            }

            p.setFilePath(webPath+mf.getOriginalFilename());
            p.setUserNo(userNo);
            p.setChangeName(changeName);
            p.setOriginName(mf.getOriginalFilename());

            result = memberService.insertUserProfile(p);

        }
        return result;
    }

    // 유저 프로필 UPDATE. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @RequestMapping(value = "/updateUserProfile")
    public int updateUserProfile(
            @RequestParam(value = "file" , required = false) MultipartFile[] ufiles ,
            @RequestParam(value = "userNo" , required = false) int userNo) {
        
        String webPath = "/resources/images/userProfile/";

        int result = 0;

        Photo p = new Photo();

        for (MultipartFile mf : ufiles) {

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

            // 5. 다 이어붙이기.
            String changeName = currentTime + random + ext;

            // 폴더에 이미지 저장.
            String savePath = "C:/LemongProject/src/main/webapp/resources/images/userProfile/";

            if (!mf.getOriginalFilename().equals("")) { // 파일명이 비어있지 않은 경우
                try {
                    mf.transferTo(new File(savePath + changeName));
                } catch (IllegalStateException | IOException e) {
                    System.out.println(e.getMessage() + "오류 발생");
                }
            }
                
            p.setFilePath(webPath+mf.getOriginalFilename());
            p.setUserNo(userNo);
            p.setChangeName(changeName);
            p.setOriginName(mf.getOriginalFilename());

            result = memberService.updateUserProfile(p);

        }
        return result;

    }

    @GetMapping("/myprofile")
    public ResponseBody<MyProfileVo> getLogin(@SessionAttribute("loginUser") Member loginMember){

//        List<Member> mList = memberService.userSelect(loginMember);
        MyProfileVo glv=memberService.getMyProfile(loginMember.getUserNo());
        if(glv!=null) {
            return ResponseBuilder.success(glv);
        }else
        {
            return ResponseBuilder.success(glv);
        }
        }

    // userNo에 해당하는 member 데이터 가져오기.
    @GetMapping("/selectMember")
    public ResponseBody<Member> selectMember(@RequestParam(value = "userNo" , required = false) int userNo){

        System.out.println(userNo + " = = = ");
        Member m = memberService.seletMember(userNo);
        return ResponseBuilder.success(m);
    }

    // userNo에 해당하는 user 프로필 정보 가져오기(changeName 포함).
    @GetMapping("/selectMyProfile")
    public ResponseBody<Profile> selectMyProfile(@RequestParam(value="userNo" , required = false) int userNo){

        Profile p = memberService.selectMyProfile(userNo);
        return ResponseBuilder.success(p);
    }

    // 검색 기능(유저 아이디 검색)
    @GetMapping("/searchUser")
    public ResponseBody<Profile> searchUser(@RequestParam(value="userNick" , required = false) String userNick){

        System.out.println(userNick + " : success");

        List<Profile> p = memberService.searchUser(userNick);

        System.out.println(p + " : success");

        return ResponseBuilder.success(p);
    }

}