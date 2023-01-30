package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;
    final private FileUtil fileUtil;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

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



    // 마이페이지 회원정보 수정
//    @GetMapping("/selectPro")
//    public List<Profile> MyPageUpdate(@SessionAttribute()){
//
//        List<Profile> mList = memberService.mypage();
//
//        return mList;
//    }
//
//    // MEMBER테이블 유저 정보 조회.
//    @GetMapping("/selectUser")
//    public List<Member> selectUser(){
//        List<Member> mList = memberService.selectUser();
//        ResponseBody<List<Member>> r= ResponseBuilder.success(mList);
//        return mList;
//    }

    // USER_PROFILE테이블 유저 닉네임 업데이트.
    @GetMapping("/checkNickName")
    public int updateUser(@SessionAttribute("loginUser")Member loginUser,@RequestParam(value="modifyNickname" , required=false) String nickName){

        Profile p=new Profile();
        p.setUserNo(loginUser.getUserNo());
        p.setNickName(nickName);
        int upList = memberService.updateProfile(p);

        System.out.println(nickName);

        return upList;
    }

    // USER_PROFILE테이블 유저 자기소개 업데이트.
    @GetMapping("/updateComment")
    public int updateComment(@SessionAttribute("loginUser")Member loginUser,@RequestParam(value="modifyComment" , required = false) String comment){
        Profile p=new Profile();
        p.setUserNo(loginUser.getUserNo());
        p.setProfileComment(comment);
        int upList2 = memberService.updateProfile(p);
        return upList2;
    }

    @GetMapping("/myPwdUpdate")
    public int myupdatePwd(@SessionAttribute("loginUser") Member loginUser,@RequestParam(value = "upPwd" , required = false)String upPwd){
        String pwd=bCryptPasswordEncoder.encode(upPwd);
        int userNo=loginUser.getUserNo();
        ChangePwdVo cpw=new ChangePwdVo(userNo,pwd);
        int result = memberService.updatePassword(cpw);
        return result;
    }

    // 유저 프로필 INSERT. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @PostMapping("/insertUserProfile")
//    @RequestMapping(value="/insertUserProfile", method=RequestMethod.POST)
    public ResponseBody<Photo> insertProfilePhoto(
            @RequestParam(value="file", required=false) MultipartFile[] files,
            @SessionAttribute("loginUser")Member loginUser) {
        Photo p = new Photo();
        p.setUserNo(loginUser.getUserNo());
        fileUtil.saveFile(files[0], p);
        int result = memberService.insertUserPhoto(p);
        return ResponseBuilder.success(p);
    }

}