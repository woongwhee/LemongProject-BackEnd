package site.lemongproject.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import site.lemongproject.web.common.file.Utils;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/member")
@Transactional
public class MemberController {

    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("login")
    public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
        Member loginUser = memberService.loginMember(m);

        if(loginUser == null) {
            mv.addObject("errorMsg", "로그인 실패");
            mv.setViewName("common/errorPage");
        } else {
            session.setAttribute("loginUser", loginUser);
            mv.setViewName("redirect:/");
        }
        return mv;
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

        return mList;
    }

    // USER_PROFILE테이블 유저 닉네임 업데이트.
    @GetMapping("/updateUser")
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

    // USER_PROFILE테이블 유저 프로필 INSERT.
    @GetMapping("/insertUserProfile")
    public int insertUserProfile(
            @RequestParam(value="img" , required = false) String userProImg ,
            MultipartFile upfile ,
            HttpSession session){

        String webPath = "/resources/images/userProfile/";
        String serverFolderPath = session.getServletContext().getRealPath(webPath);

        Photo p = new Photo();

        if(!upfile.getOriginalFilename().equals("")){
                String savePath = session.getServletContext().getRealPath("/resources/images/uploadFiles/");
                String changeName = Utils.saveFile(upfile , savePath);

            try {
                upfile.transferTo(new File(savePath + changeName));
            } catch (IllegalStateException | IOException e) {
                System.out.println("오류남");
            }
            p.setChangeName("resources/uploadFiles/" + changeName);
            p.setOriginName(upfile.getOriginalFilename());
        }

        int result = memberService.insertUserProfile(p , webPath , serverFolderPath);

        return result;
    }


}
