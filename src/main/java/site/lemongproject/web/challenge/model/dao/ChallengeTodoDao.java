package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.ChallengeTodo;

import java.util.List;

public interface ChallengeTodoDao {
    int insertTodoList(List<ChallengeTodo> challengeTodoList);
}
