package site.lemongproject.common.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChallengeUserStatus implements NameEnum {
    READY(0,"READY","챌린지시작전 준비완료상태"),
    PLAY(1,"PLAY","챌린지참여중"),
    CANCEL(2,"CANCEL","중도포기상태"),
    FAIL(3,"FAIL","실패"),
    CLEAR(4,"CLEAR","성공");
    final private int index;
    final private String dbName;
    final private String krInfo;
    @Override
    public String getName() {
        return this.dbName;
    }
}
