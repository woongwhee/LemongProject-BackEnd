package site.lemongproject.web.template.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.feed.domain.vo.Feed;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.template.model.vo.Template;
import site.lemongproject.web.template.service.TemplateService;

@RequestMapping("/template/insert")
@RestController
@RequiredArgsConstructor
public class TemplateInsertController {
    final private TemplateService templateService;
    @GetMapping("/")
    public ResponseBody<Template> load( Member loginMember){
        Template cur=templateService.loadInsertPage(loginMember.getUserNo());
        ResponseBody<Template> responseBody=new ResponseBody<>();
        responseBody.setCode("2000");
        responseBody.setMessage("SUCCESS");
        ResponseBody<Template> response= ResponseBuilder.success(cur);
        return response;
    }
    @GetMapping(value = "/save")
    public @org.springframework.web.bind.annotation.ResponseBody ResponseBody<Integer> save(@SessionAttribute("loginUser") Member loginUser){
        ResponseBody<Integer> response =new ResponseBody<>();
        return response;
    }

}
