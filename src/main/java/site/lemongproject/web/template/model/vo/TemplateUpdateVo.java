package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("TemplateUpdateVo")
public class TemplateUpdateVo {
    private int userNo;
    private Integer templateNo;
    private Integer categoryNo;
    private Integer range;
    private String title;
    private String content;
}
