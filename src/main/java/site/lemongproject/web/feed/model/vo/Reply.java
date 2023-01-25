package site.lemongproject.web.feed.model.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class Reply {
    private int replyNo;
    private int userNo;
    private int feedNo;
    private String replyContent;
    private Date replyAt;
}
