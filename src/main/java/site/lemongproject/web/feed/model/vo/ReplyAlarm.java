package site.lemongproject.web.feed.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class ReplyAlarm {
    private int replyNo;
    private int userNo; // 보낸사람
    private int feedNo;
    private String replyContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date replyAt;
    private int recNo; // 받은사람
}
