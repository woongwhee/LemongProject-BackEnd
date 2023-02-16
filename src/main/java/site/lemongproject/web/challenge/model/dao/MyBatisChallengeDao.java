package site.lemongproject.web.challenge.model.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.model.vo.*;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MyBatisChallengeDao implements ChallengeDao{
    final private SqlSession session;

    @Override
    public int insertSingle(SingleStartVo startVo) {
        return session.insert("challengeMapper.insertSingle",startVo);
    }

    @Override
    public int insertMulti(MultiCreateVo createVo) {
        return session.insert("challengeMapper.insertMulti",createVo);
    }


    @Override
    public int updateEndDate(EndDateUpdateVo endDateUpdateVo) {
        return session.update("challengeMapper.updateEndDate",endDateUpdateVo);
    }



    @Override
    public List<Challenge> selectChallenge(){
        return session.selectList("challengeMapper.selectChallenge");
    }

    @Override
    public List<Challenge> detailChallenge(Challenge c){
        return session.selectList("challengeMapper.detailChallenge" , c);
    }

    @Override
    public int challengeGo(ChallengeUser u){
        return session.insert("challengeMapper.challengeGo" , u);
    }

    @Override
    public Challenge findOne(int challengeNo) {
        return session.selectOne("challengeMapper.findOne" , challengeNo);
    }

    @Override
    public List<ChallengeListVo> findReady(int page, int limit) {
        int offSet=page*limit;
        RowBounds rowBounds=new RowBounds(offSet,limit);
        return session.selectList("challengeMapper.findReady",null,rowBounds);
    }

    @Override
    public List<ChallengeUser> myChallengeList(ChallengeUser u){
        return session.selectList("challengeUserMapper.myChallengeList" , u);
    }

    @Override
    public ChallengeDetailVo findDetail(int challengeNo) {
        return session.selectOne("challengeMapper.findDetail",challengeNo);
    }

    @Override
    public int startChallenge() {
        return session.update("challengeMapper.challengeStart");
    }

    @Override
    public int finishChallenge() {
        return session.update("challengeMapper.challengeFinish");
    }

    @Override
    public ChallengeRoomVo findRoom(int challengeNo) {
        return session.selectOne("challengeMapper.findRoom",challengeNo);
    }

    @Override
    public int cancelChallenge(int challengeNo){
        return session.update("challengeMapper.cancelChallenge",challengeNo);
    }
}
