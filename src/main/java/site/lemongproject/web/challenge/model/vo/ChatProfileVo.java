package site.lemongproject.web.challenge.model.vo;

import org.apache.ibatis.type.Alias;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.web.photo.model.vo.Photo;
@Alias("ChatProfileVo")
public class ChatProfileVo {
    private int userNo;
    private String nickName;
    private Photo photo;
    private int percent;
    private ChallengeUserStatus status;
}
