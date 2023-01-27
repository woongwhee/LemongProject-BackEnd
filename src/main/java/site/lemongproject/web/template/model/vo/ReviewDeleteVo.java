package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("ReviewDeleteVo")
public class ReviewDeleteVo {
    int reviewNo;
    int userNo;
}
