package site.lemongproject.web.challenge.model.vo;

import site.lemongproject.web.todo.model.vo.Todo;

import java.time.LocalDate;
import java.util.List;

public class ChallengeTodoVo {
    String challengeName;
    LocalDate todoDate;
    List<Todo> todoList;
}
