package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TempalteTodoInsertVo {
    private List<Integer> dayList;
    private String content;
    private int templateNo;
}
