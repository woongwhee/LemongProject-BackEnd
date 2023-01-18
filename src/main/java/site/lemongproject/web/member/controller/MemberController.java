package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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



    @PostMapping("login")
    public ResponseBody<Member> loginMember(@RequestBody Member m) {
//        Member m=new Member();
//        m.setEmail(email);
//        m.setUserPwd(userPwd);
//        System.out.println(request.getParameterNames());
        //System.out.println(member);
        Member loginUser = memberService.loginMember(m);
        if(loginUser!=null){
            return ResponseBuilder.success(loginUser);
        }else{
            System.out.println("컨트롤러 못 넘어옴");
            return ResponseBuilder.unLogin(null);
        }


    }




}
