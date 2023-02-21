package site.lemongproject.common.type;

public enum SocialType implements NameEnum {
    NAVER("NAVER"),KAKAO("KAKAO"),NONE("NONE");
    private String name;

    SocialType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
