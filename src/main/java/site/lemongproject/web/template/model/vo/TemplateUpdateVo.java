package site.lemongproject.web.template.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateUpdateVo {
    private Integer templateNo;
    private Integer categoryNo;
    private Integer range;
    private String title;
    private String content;
}
