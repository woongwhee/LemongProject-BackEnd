package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import site.lemongproject.common.domain.dto.MailMessage;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.common.util.MailUtil;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
    final private MemberService memberService;
    final private FileUtil fileUtil;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private MailUtil mailUtil;



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
    public ResponseBody<Photo> insertUserProfile(
            @RequestParam(value="file", required=false) MultipartFile[] files) {

        Photo p = new Photo();
        p.setUserNo(2);
        fileUtil.saveFile(files[0], p);
        int result = memberService.insertUserProfile(p);
//        if(result>0){
//        }
        return ResponseBuilder.success(p);
        }

    }