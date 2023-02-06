package site.lemongproject.web.feed.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class FeedInsert {
    private int userNo;
    private String feedContent;
    private List<Integer> photoNo;
    private int feedNo;
}


