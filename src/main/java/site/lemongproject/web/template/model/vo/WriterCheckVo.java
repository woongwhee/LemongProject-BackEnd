package site.lemongproject.web.template.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
@Getter
@Setter
@AllArgsConstructor
@Alias("WriterCheckVo")
public class WriterCheckVo {
    private int userNo;
    private int refNo;
}
