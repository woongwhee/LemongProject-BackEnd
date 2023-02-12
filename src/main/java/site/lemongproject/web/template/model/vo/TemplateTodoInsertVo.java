package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;
@Getter
@Setter
@ToString
@Alias("TemplateTodoInsertVo")
public class TemplateTodoInsertVo {
    private int userNo;
    private List<Integer> dayList;
    private List<String> contentList;
    private int templateNo;
}
