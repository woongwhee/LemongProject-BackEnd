package site.lemongproject.web.feed.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FeedInsertPhoto {
    private int feedNo;
    private int photoNo;
    private int value;
}
