package site.lemongproject.web.feed.model.vo;


import lombok.Data;

import java.sql.Date;

@Data
public class Feed {
    private int feedNo;
    private int userNo;
    private String feedContent;
    private Date feedAt;
}
