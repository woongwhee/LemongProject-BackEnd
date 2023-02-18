package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import site.lemongproject.web.template.model.dto.TemplateCategory;
@Getter
@Setter
@Alias("TemplateListVo")
public class TemplateListVo {
    private int templateNo;
    private String title;
    private String content;
    private int todoCount;
    private int clearCount;
    private int playCount;
    private TemplateCategory category;
}
