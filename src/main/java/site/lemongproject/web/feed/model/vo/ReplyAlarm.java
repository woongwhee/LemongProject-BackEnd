package site.lemongproject.web.feed.model.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReplyAlarm {
    private int replyNo;
    private int userNo; // 보낸사람
    private int feedNo;
    private String replyContent;
    private LocalDate replyAt;
    private int recNo; // 받은사람
}
