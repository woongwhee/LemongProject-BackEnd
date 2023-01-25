package site.lemongproject.web.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.todo.model.vo.Todo;
import site.lemongproject.web.todo.service.TodoService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    final private TodoService todoService;

    @GetMapping("/getTodo")
    public List<Todo> selectToDo(Date date, Member loginUser){

//        List<Todo> t = todoService.selectToDo(loginUser.getUserNo(),date);
        List<Todo> t = todoService.selectToDo();

        return t;
    }

    @GetMapping("/insert")
    public String todoCreate(Todo t, @RequestParam(value = "Todo",  required = false) List<Todo> tdl){
        int result = 0;
        result = todoService.insertTodo(t, tdl);

        return "redirect:/";

    }

//    @GetMapping("/test")
//    public String test(@RequestParam value="" required=""){
//        int result = 0;
//        result = todoService.updateTodo();
//    }


}
