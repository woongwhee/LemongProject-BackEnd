package site.lemongproject.web.template.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Alias("TemplateFindVo")
public class TemplateFindVo {
    private int page;
    private int categoryNo;
    private int templateNo;
    private int userNo;

    public TemplateFindVo(int templateNo, int userNo) {
        this.templateNo = templateNo;
        this.userNo = userNo;
    }

    public TemplateFindVo(int page, int categoryNo, int userNo) {
        this.page = page;
        this.categoryNo = categoryNo;
        this.userNo = userNo;
    }
}
