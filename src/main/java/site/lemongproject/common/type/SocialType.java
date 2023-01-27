package site.lemongproject.common.type;

import site.lemongproject.common.type.handler.NameEnum;

public enum SocialType implements NameEnum {
    NAVER,KAKAO,NONE;

    SocialType() {
    }

    @Override
    public String getName() {
        return this.name().toUpperCase();
    }
}
