package site.lemongproject.web.feed.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class Reply {
    private int replyNo;
    private int userNo;
    private int feedNo;
    private String replyContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyAt;
    private String nickName;
}
