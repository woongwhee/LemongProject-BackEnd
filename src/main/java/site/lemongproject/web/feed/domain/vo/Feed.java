package site.lemongproject.web.feed.domain.vo;


import lombok.Data;

import java.util.Date;

@Data
public class Feed {
    private int feedNo;
    private int userNo;
    private int feedContent;
    private Date feedAt;
}
