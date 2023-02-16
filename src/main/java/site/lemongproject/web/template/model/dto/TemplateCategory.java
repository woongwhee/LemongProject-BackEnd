package site.lemongproject.web.template.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@ToString
@Alias("TemplateCategory")
public class TemplateCategory {
    private int categoryNo;
    private String categoryName;
    private String imagePath;
}
