package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("TemplateCreateVo")
public class TemplateCreateVo {
    int userNo;
    int templateNo;
}
