package site.lemongproject.web.template.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.*;
import site.lemongproject.web.template.service.TemplateReadService;
import site.lemongproject.web.template.service.TemplateWriteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateController {
    final private TemplateWriteService tpWriteService;
    final private TemplateReadService tpReadService;

    @GetMapping(value = {"/list", "/list/{page}", "/list/{page}/{categoryNo}"})
    public ResponseBody<List<Template>> list(@PathVariable(value = "page", required = false) Optional<Integer> p,
                                             @PathVariable(value = "categoryNo", required = false) Optional<Integer> cno,
    @SessionAttribute("loginUser")Profile loginUser) {
        int categoryNo = cno.orElse(0);//없는 경우 모든 카테고리
        int page = p.orElse(0);//없는 경우 0페이지
        TemplateFindVo findVo=new TemplateFindVo(page,categoryNo, loginUser.getUserNo());
        List<Template> templateList = tpReadService.getTemplateList(findVo);
        if (templateList != null) {
            return ResponseBuilder.success(templateList);
        } else {
            return ResponseBuilder.findNothing();
        }
    }
    @GetMapping(value = {"/count/{categoryNo}"})
    public ResponseBody<Integer> count(@PathVariable(value = "categoryNo", required = false) Optional<Integer> cno) {
        int categoryNo = cno.orElse(0);//없는 경우 모든 카테고리
        int count = tpReadService.getTemplateCount(categoryNo);
        System.out.println(count);
        return ResponseBuilder.success(count);
    }
    @GetMapping(value = {"/one/{templateNo}"})
    public ResponseBody<List<TemplateDetailVo>> list(@PathVariable(value = "templateNo") int templateNo,@SessionAttribute("loginUser")Profile loginUser) {
        TemplateDetailVo template = tpReadService.getTemplateDetail(new TemplateFindVo(templateNo,loginUser.getUserNo()));
        if (template != null) {
            return ResponseBuilder.success(template);
        } else {
            return ResponseBuilder.findNothing();
        }
    }
    @GetMapping("/category")
    public ResponseBody<List<TemplateCategory>> category(){
        List<TemplateCategory> categoryList= tpReadService.getTemplateCategory();
        if(categoryList.size()>0){
            return ResponseBuilder.success(categoryList);
        }else{
            return ResponseBuilder.findNothing();
        }

    }

    @GetMapping("/unsave/load")
    public ResponseBody<TPUnsaveVo> load(@SessionAttribute("loginUser") Profile loginMember) {
        TPUnsaveVo cur = tpWriteService.loadInsertPage(loginMember.getUserNo());
        return ResponseBuilder.success(cur);
    }
    @GetMapping("/todo/detail/{templateNo}/{day}")
    public ResponseBody<List<TemplateTodo>> todoDetail( @PathVariable("templateNo")int templateNo,@PathVariable("day")int day) {
        List<TemplateTodo> todoList = tpReadService.getTodoByDay(new TPDayTodoVo(templateNo,day));
        if(todoList!=null&&todoList.size()>0){
            return ResponseBuilder.success(todoList);
        }else{
            return ResponseBuilder.findNothing();
        }
    }
    @PutMapping(value = "/unsave/upload")
    public ResponseBody<Integer> upload(@SessionAttribute("loginUser") Profile loginUser) {
        int result = tpWriteService.uploadUnSave(loginUser.getUserNo());
        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.upLoadFail();
        }
    }

    @PutMapping("/unsave/update")
    public ResponseBody<Integer> updateUnSave(@SessionAttribute("loginUser") Profile loginUser, @RequestBody TemplateUpdateVo tuv) {
        System.out.println(tuv);
        if ((tuv == null || tuv.getTemplateNo() == null) ||
                (tuv.getRange() == null && tuv.getTitle() == null && tuv.getCategoryNo() == null && tuv.getContent() == null)) {
            return ResponseBuilder.upLoadFail();
        }
        System.out.println(tuv);

        tuv.setUserNo(loginUser.getUserNo());
        int result = tpWriteService.updateUnSaveTemplate(tuv);
        if (result > 0) {
            return ResponseBuilder.success(1);
        } else {
            return ResponseBuilder.upLoadFail();
        }
    }

    @PutMapping("/unsave/reset")
    public ResponseBody<TPUnsaveVo> resetUnSave(@SessionAttribute("loginUser") Profile loginUser) {

        TPUnsaveVo t = tpWriteService.resetUnSave(loginUser.getUserNo());
        if (t != null) {
            return ResponseBuilder.success(t);
        } else {
            return ResponseBuilder.upLoadFail();
        }
    }


    @PostMapping("/todo/insert")
    public ResponseBody<List<Integer>> insertTodo(@SessionAttribute("loginUser")Profile loginUser, @RequestBody TemplateTodoInsertVo tiv) {
        tiv.setUserNo(loginUser.getUserNo());
        int result = tpWriteService.insertTodo(tiv);
        if (result>0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.upLoadFail();
        }
    }

    @DeleteMapping("/todo/deleteUnSave/{tpTodoNo}")
    public ResponseBody<Integer> deleteTodo(@SessionAttribute("loginUser") Profile loginUser, @PathVariable("tpTodoNo") int tpTodoNo) {
        int result = tpWriteService.deleteTodo(new TPTodoDeleteVo(loginUser.getUserNo(), tpTodoNo));

        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.deleteFail();
        }
    }
    @DeleteMapping("/delete/{templateNo}")
    public ResponseBody<Integer> deleteTemplate(@SessionAttribute("loginUser") Profile loginUser, @PathVariable("templateNo") int templateNo) {
        int result = tpReadService.deleteTemplate(loginUser.getUserNo(),templateNo);
        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.deleteFail();
        }
    }


    @PostMapping("/review/insert")
    public ResponseBody<Integer> reviewInsert(@SessionAttribute("loginUser")Profile loginUser, @RequestBody ReviewInsertVo riv){
        riv.setUserNo(loginUser.getUserNo());
        System.out.println(riv);
        int result= tpReadService.insertReview(riv);
        if(result>0){
            return ResponseBuilder.success(result);
        }else{
            return ResponseBuilder.upLoadFail();
        }
    }
    @DeleteMapping("/review/delete/{reviewNo}")
    public ResponseBody<Integer> reviewDelete(@SessionAttribute("loginUser")Member loginUser, @PathVariable("reviewNo")int reviewNo){
        ReviewDeleteVo reviewDeleteVo=new ReviewDeleteVo(reviewNo,loginUser.getUserNo());
        int result= tpReadService.deleteReview(reviewDeleteVo);
        if(result>0){
            return ResponseBuilder.success(result);
        }else{
            return ResponseBuilder.upLoadFail();
        }
    }
    @GetMapping("/review/list/{templateNo}")
    public ResponseBody<List<Review>> reviewList(@PathVariable("templateNo")int templateNo){
        List<Review> rList= tpReadService.getReviewList(templateNo);
        if(rList!=null){
            return ResponseBuilder.success(rList);
        }else{
            return ResponseBuilder.findNothing();
        }
    }


    @GetMapping("/review/one/{reviewNo}")
    public ResponseBody<Review> reviewOne(@PathVariable("reviewNo")int reviewNo){
        Review review= tpReadService.getReviewOne(reviewNo);
        if(review!=null){
            return ResponseBuilder.success(review);
        }else{
            return ResponseBuilder.findNothing();
        }
    }


}
