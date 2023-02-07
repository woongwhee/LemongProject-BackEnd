package site.lemongproject.web.todo.model.dto;

import lombok.Getter;
import lombok.Setter;
import site.lemongproject.web.challenge.model.vo.ChallengeTodoVo;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;
@Getter
@Setter
//반환용 그릇
public class DailyTodoVo {
    private List<Todo> normalList;
    private List<ChallengeTodoVo> challengeList;
}
