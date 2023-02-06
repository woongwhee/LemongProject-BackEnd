package site.lemongproject.common.type;

public enum SocialType implements NameEnum {
    NAVER,KAKAO,NONE;

    SocialType() {
    }

    @Override
    public String getName() {
        return this.name().toUpperCase();
    }
}
