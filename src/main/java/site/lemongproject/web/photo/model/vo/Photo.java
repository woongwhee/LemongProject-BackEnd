package site.lemongproject.web.photo.model.vo;

import lombok.Data;

@Data
public class Photo {
    private int photoNo;
    private int userNo;
    private String originName;
    private String changeName;
    private String filePath;
}
