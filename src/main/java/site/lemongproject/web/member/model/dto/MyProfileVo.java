package site.lemongproject.web.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
@AllArgsConstructor
@Getter
public class MyProfileVo {
    private Member member;
    private Profile profile;
}
