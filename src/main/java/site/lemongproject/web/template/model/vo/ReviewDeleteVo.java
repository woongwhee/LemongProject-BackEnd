package site.lemongproject.web.template.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@AllArgsConstructor
@Alias("ReviewDeleteVo")
public class ReviewDeleteVo {
    int reviewNo;
    int userNo;
}
