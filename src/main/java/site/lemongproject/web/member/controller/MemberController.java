package site.lemongproject.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;

import javax.servlet.http.HttpSession;

@RestController
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




}
