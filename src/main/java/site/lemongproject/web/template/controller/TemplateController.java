package site.lemongproject.web.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.template.model.vo.Template;

import java.sql.Date;

@RestController
@RequestMapping("/template/one")
public class TemplateController {
    @GetMapping(value="/login", produces = "application/json;charset=UTF8")
    public ResponseBody<Template> test(){

        Template t=new Template();
        t.setContent("안녕");
        t.setTitle("안녕");
        t.setRange(30);
        t.setSaveStatus(true);
        ResponseBody<Template> result= ResponseBuilder.success(t);
        return result;
    }

}
