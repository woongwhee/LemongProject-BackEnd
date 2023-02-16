package site.lemongproject.web.challenge.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@AllArgsConstructor
@Alias("TodoClearVo")
public class TodoClearVo {
    long todoNo;
    int templateNo;
    int userNo;
}
