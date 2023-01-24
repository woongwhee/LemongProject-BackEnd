package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;
    final private FileUtil fileUtil;

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
            p.setUserNo(2);
            p.setChangeName(changeName);
            p.setOriginName(mf.getOriginalFilename());

            result = memberService.insertUserProfile(p);

        }
        return result;
    }

    // 유저 프로필 UPDATE. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @RequestMapping(value = "/updateUserProfile")
    public int updateUserProfile(
            @RequestParam(value = "file" , required = false) MultipartFile[] ufiles){

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
            p.setUserNo(2);
            p.setChangeName(changeName);
            p.setOriginName(mf.getOriginalFilename());

            result = memberService.updateUserProfile(p);

        }
        return result;

    }

    @GetMapping("/selectMyProfileImg")
    // 프로필 사진 뽑아서 리액트로 보내기
    public List<Photo> selectMyProfile(){

        List<Photo> pList = memberService.selectMyProfile();

        return pList;
    }

    @GetMapping("/deleteUser")
    public int deleteUser(){
        int result = memberService.deleteUser();
        return result;
    }

}
