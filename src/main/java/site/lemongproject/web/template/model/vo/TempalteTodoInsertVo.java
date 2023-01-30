package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;
@Getter
@Setter
@Alias("TempalteTodoInsertVo")
public class TempalteTodoInsertVo {
    private int userNo;
    private List<Integer> dayList;
    private String content;
    private int templateNo;
}
