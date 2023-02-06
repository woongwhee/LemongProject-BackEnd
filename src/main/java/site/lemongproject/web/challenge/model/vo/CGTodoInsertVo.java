package site.lemongproject.web.challenge.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;
@Getter
@Setter
@Alias("CGTodoInsertVo")
public class CGTodoInsertVo {
    private int challengeNo;
    private int userNo;
    private List<CGTodoItemVo> todoList;

}
