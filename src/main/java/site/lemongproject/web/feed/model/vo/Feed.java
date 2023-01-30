package site.lemongproject.web.feed.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import site.lemongproject.web.photo.model.vo.Photo;
import site.lemongproject.web.reply.model.vo.Reply;

import java.sql.Date;
import java.util.List;


@Data
public class Feed {
    private int feedNo;
    private int userNo;
    private String feedContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date feedAt;
    private List<Reply> replyList;
    private List<Photo> photoList;

}
