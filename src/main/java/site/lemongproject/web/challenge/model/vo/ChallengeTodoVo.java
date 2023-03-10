package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.todo.model.vo.Todo;

import java.time.LocalDate;
import java.util.List;
@Alias("ChallengeTodoVo")
@Setter
@Getter
@ToString
public class ChallengeTodoVo {
    private int challengeNo;
    private String challengeName;
    private List<Todo> todoList;
}
