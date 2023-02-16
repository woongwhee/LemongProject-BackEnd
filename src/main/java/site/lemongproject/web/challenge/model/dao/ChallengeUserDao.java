package site.lemongproject.web.challenge.model.dao;

import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.ChallengeUserVo;

public interface ChallengeUserDao {

    /**
     * challengeUser하나 추가
     * @param joinVo
     * @return
     */
    int joinUser(ChallengeUserVo joinVo);

    /**
     * claerCount 증가 혹은 감소 처리
     * @param todo
     * @return
     */
    int changeClear(ChallengeTodo todo);

    /**
     * 성능을 위해 반정규화되어 저장되어있는 clearcount,clear를 확인 후 수정
     * @return
     */
    int adjustClearCount();

    /**
     * 집계처리(스케줄링)
     * @param clear_percent
     * @return
     */
    int finishChallenge(float clear_percent);

    /**
     * 시작처리
     * @return
     */
    int startChallenge();
    /**
     * 챌린지유저 상태를 취소로 변경(포기한 유저도 조회하기 위하여)
     * @param userVo
     * @return
     */
    int cancelUser(ChallengeUserVo userVo);

    /**
     * 챌린지유저 정보를 데이터베이스에서 삭제
     * @param userVo
     * @return
     */
    int deleteUser(ChallengeUserVo userVo);

    /**
     * 챌린지에 남아있는 유저가 있는지확인
     * @param challengeNo
     * @return
     */
    int countPlayer(int challengeNo);

    boolean inChallenge(ChallengeUserVo userVo);
}
