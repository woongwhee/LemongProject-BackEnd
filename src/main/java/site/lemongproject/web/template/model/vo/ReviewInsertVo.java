package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.member.model.vo.Profile;
@Getter
@Setter
@ToString
@Alias("ReviewInsertVo")
public class ReviewInsertVo {
    int templateNo;
    int userNo;
    String content;
}
