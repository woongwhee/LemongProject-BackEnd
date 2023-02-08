package site.lemongproject.web.todo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
@Alias("MonthFindVo")
@Getter
@Setter
@ToString
public class MonthFindVo {
    private int userNo;
    @JsonFormat(pattern = "yyMMdd", timezone = "GMT+9")
    private LocalDate month;


}
