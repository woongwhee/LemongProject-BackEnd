package site.lemongproject.web.template.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseEntity;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.template.model.vo.Template;
import site.lemongproject.web.template.service.TemplateService;

@RequestMapping("/template/insert")
@RestController
@RequiredArgsConstructor
public class TemplateInsertController {
    final private TemplateService templateService;
    @GetMapping("/")
    public @ResponseBody ResponseEntity<Template> load(@SessionAttribute("loginUser") Member loginMember){
        Template cur=templateService.loadInsertPage(loginMember.getUserNo());

        ResponseEntity<Template> response =new ResponseEntity<>();

        return response;
    }
    @GetMapping(value = "/save")
    public @ResponseBody ResponseEntity<Integer> save(@SessionAttribute("loginUser") Member loginUser){


        ResponseEntity<Integer> response =new ResponseEntity<>();
        return response;
    }

}
