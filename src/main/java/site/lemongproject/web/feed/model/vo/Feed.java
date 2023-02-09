package site.lemongproject.web.feed.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;


@Data
public class Feed {
    private int feedNo;
    private int userNo;
    private String feedContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date feedAt;
}
