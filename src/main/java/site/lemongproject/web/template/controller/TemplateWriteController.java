package site.lemongproject.web.template.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.service.TemplateWriteService;

@RequestMapping("/template/write")
@RestController
@RequiredArgsConstructor
public class TemplateWriteController {
    final private TemplateWriteService templateService;
    @GetMapping("/load")
    public ResponseBody<Template> load( Member loginMember){
        Template cur=templateService.loadInsertPage(loginMember.getUserNo());
        ResponseBody<Template> responseBody=new ResponseBody<>();
        responseBody.setCode("2000");
        responseBody.setMessage("SUCCESS");
        ResponseBody<Template> response= ResponseBuilder.success(cur);
        return response;
    }
    @GetMapping(value = "/upload")
    public ResponseBody<Integer> upload(@SessionAttribute("loginUser") Member loginUser){
        ResponseBody<Integer> response =new ResponseBody<>();
        int result=templateService.saveTemplate(loginUser.getUserNo());

        return response;
    }

}
