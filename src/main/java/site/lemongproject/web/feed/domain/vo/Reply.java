package site.lemongproject.web.feed.domain.vo;

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
