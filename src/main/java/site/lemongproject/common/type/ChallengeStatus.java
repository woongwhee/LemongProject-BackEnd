package site.lemongproject.common.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.lemongproject.common.type.handler.NameEnum;
@Getter
@RequiredArgsConstructor
public enum ChallengeStatus implements NameEnum {
    PLAY(0,"PLAY","시작됨"),
    READY(1,"READY","시작일이 아직 아님"),
    FINISH(2,"FINISH","완료된 상태"),
    CANCEL(3,"CANCEL","전원포기로 취소됨"),
    SINGLE(4,"SINGLE","혼자 시작한 챌린지");
    final private int index;
    final private String dbName;
    final private String krInfo;

    @Override
    public String getName() {
        return this.dbName;
    }
}
