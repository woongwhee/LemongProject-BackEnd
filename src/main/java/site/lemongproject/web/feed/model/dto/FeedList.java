package site.lemongproject.web.feed.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import site.lemongproject.web.photo.model.vo.Photo;

import java.sql.Date;
import java.util.List;

@Data
public class FeedList {
    private int feedNo;
    private int userNo;
    private String nickName;
    private String feedContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date feedAt;
    private String filePath;
    private int photoNo;
    private Photo photo;
//    private FeedInsertPhoto feedInsertPhoto;
}
