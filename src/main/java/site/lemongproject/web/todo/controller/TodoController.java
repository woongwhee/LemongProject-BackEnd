package site.lemongproject.web.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.web.todo.model.vo.Todo;
import site.lemongproject.web.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    final private TodoService todoService;

    @GetMapping("/getTodo")
    public List<Todo> selectToDo(){

        List<Todo> t = todoService.selectToDo();

        return t;
    }

    @GetMapping("/insert")
    public String todoCreate(Todo t, @RequestParam(required = false)int id){
        int result = 0;
        result = todoService.insertTodo(t);

        if(result > 0){
            return "todo/selectTodo";
        }

        return null;
    }

//    @GetMapping("/test")
//    public String test(@RequestParam value="" required=""){
//        int result = 0;
//        result = todoService.updateTodo();
//    }


}
