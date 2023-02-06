package site.lemongproject.web.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.todo.model.dto.DailyFindVo;
import site.lemongproject.web.todo.model.dto.DailyTodoVo;
import site.lemongproject.web.todo.model.vo.Todo;
import site.lemongproject.web.todo.service.TodoService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/todo", method = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class TodoController {

    final private TodoService todoService;

    //투두 목록 가져오기
    @GetMapping ("/getTodo")
    public List<Todo> selectTodo(@RequestParam(value = "todoDate" , required = false)
                                     @DateTimeFormat(pattern = "yyMMdd")
                                     LocalDate todoDate,
                                 @RequestParam(value = "userNo", required = false) int userNo) throws ParseException {
//        List<Todo> t = todoService.selectToDo(loginUser.getUserNo(),date);

//        System.out.println("넘겨준 userNo: "+userNo);
//        System.out.println("넘겨준 todoDate: "+todoDate);

        Todo t = new Todo();
        t.setUserNo(userNo);
        t.setTodoDate(todoDate);
        List<Todo> todoList = todoService.selectTodo(t);
        return todoList;
    }
    @GetMapping("/daily/{todoDate}")
    public ResponseBody<DailyTodoVo> getDaily(
            @PathVariable("todoDate")
            @DateTimeFormat(pattern = "yyMMdd")
            LocalDate todoDate){
//            @SessionAttribute("loginUser")Member member){
        DailyFindVo dailyFind=new DailyFindVo();
        dailyFind.setTodoDate(todoDate);
        dailyFind.setUserNo(31);
        DailyTodoVo daily=todoService.getDaily(dailyFind);
        if(daily.getNormalList().size()==0&&daily.getChallengeList().size()==0){
            return ResponseBuilder.findNothing();
        }
        else if(daily.getChallengeList()!=null && daily.getNormalList()!=null){
            return ResponseBuilder.success(daily);
        }else{
            return ResponseBuilder.serverError();
        }
    }
    //투두 작성
    @PostMapping("/insertTodo")
    public ResponseBody<Todo> insertTodo(@RequestBody Todo t){

        System.out.println("t : "+t);

        todoService.insertTodo(t);

        return ResponseBuilder.success(t);
    }

    //투두 삭제
    @GetMapping("/deleteTodo")
    public ResponseBody<Todo> deleteTodo(@RequestParam(value = "todoNo") int todoNo){

        System.out.println("delete todoNo: "+todoNo);

        Todo t = new Todo();
        t.setTodoNo(todoNo);

        todoService.deleteTodo(t);

        return ResponseBuilder.success(t);

    }

    //투두 완료
    @GetMapping("/clearTodo")
    public ResponseBody<Todo> clearTodo(@RequestParam(value = "todoNo" , required = false) int todoNo){

        System.out.println("clear todoNo: "+todoNo);
        Todo t = new Todo();
        t.setTodoNo(todoNo);
        todoService.clearTodo(t);
        return ResponseBuilder.success(t);
    }

    //투두 수정하기
    @GetMapping("/updateTodo")
    public ResponseBody<Todo> updateTodo(@RequestParam(value="todoNo" , required=false) int todoNo,
                                         @RequestParam(value = "todoContent" , required = false)String todoContent){

        System.out.println("update todoNo: "+todoNo);

        Todo t = new Todo();
        t.setTodoNo(todoNo);
        t.setTodoContent(todoContent);

        todoService.updateTodo(t);

        return ResponseBuilder.success(t);
    }

    //투두 내일로 미루기
    @GetMapping("/delayTodo")
    public ResponseBody<Todo> delayTodo(@RequestParam(value = "todoNo", required = false) int todoNo){

        System.out.println("delay todoNo: "+todoNo);
        Todo t = new Todo();
        t.setTodoNo(todoNo);

        todoService.delayTodo(t);

        return ResponseBuilder.success(t);
    }

    //캘린더에 투두 표시
    @GetMapping("/calTodo")
    public  List<Todo> calTodo(@RequestParam(value = "userNo", required = false) int userNo){
        System.out.println("cal userNo: "+userNo);

        Todo t = new Todo();
        t.setUserNo(userNo);

        List<Todo> calTodos = todoService.calTodo(t);

        return calTodos;
    }

    @GetMapping("/dndTodo")
    public ResponseBody<Todo> dndTodo(@RequestParam(value="todoNo" , required=false) int todoNo,
                                         @RequestParam(value = "value" , required = false)int value){

        System.out.println("dnd todoNo: "+todoNo);

        Todo t = new Todo();
        t.setTodoNo(todoNo);
        t.setValue(value);

        todoService.updateTodo(t);

        return ResponseBuilder.success(t);
    }
    
    
    
    
    
    
    
    
    
    


}
