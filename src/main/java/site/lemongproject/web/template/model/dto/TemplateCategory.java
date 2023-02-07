package site.lemongproject.web.template.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TemplateCategory {
    private int categoryNo;
    private String categoryName;
    private String imagePath;
}
