package site.lemongproject.web.feed.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyAlarmList {
    private int alrNo;
    private int userNo; // 받은사람
    private int refType;
    private int refNo;
    private int sendUser; // 보낸사람
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date alrAt;
    private int read;
}
