package site.lemongproject.web.toDo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.web.toDo.model.vo.ToDo;
import site.lemongproject.web.toDo.service.ToDoListService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoListController {

    final private ToDoListService toDoListService;

    @GetMapping("/getTodo")
    public List<ToDo> selectToDo(){

        List<ToDo> t = toDoListService.selectToDo();

        return t;

    }


}
