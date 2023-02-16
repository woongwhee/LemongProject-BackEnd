package site.lemongproject.web.challenge.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.config.Configure;
import site.lemongproject.web.challenge.model.vo.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
public class ChallengeServiceImplTest extends Configure {
    @Autowired ChallengeService challengeService;
    final int TEST_TP_NO=30;
    final int TEST_USER_NO=31;
    final int TEST_CL_NO=3000;


    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    @DisplayName("password")
    public void login(){
        String admin=bCryptPasswordEncoder.encode("admin");
        System.out.println(admin);
    }
    @Test
    public void createMulti() {
        MultiCreateVo createVo=new MultiCreateVo();
        createVo.setUserNo(TEST_USER_NO);
        createVo.setTemplateNo(TEST_TP_NO);
        createVo.setOption("10000111");
        createVo.setChallengeTitle("제목입니다,");
        createVo.setChallengeInfo("내용입니다.,");
        createVo.setStartDate(LocalDate.now().plusDays(3));
        int result=challengeService.createMulti(createVo);

    }

    @Test
    public void startSingle() {
        SingleStartVo startVo=new SingleStartVo();
        startVo.setUserNo(TEST_USER_NO);
        startVo.setStartDate(LocalDate.now());
        startVo.setTemplateNo(TEST_TP_NO);
        startVo.setStatus(ChallengeStatus.READY);
        startVo.setOption("11011011");
        int result=challengeService.startSingle(startVo);
        assertThat(result).isNotZero();
    }
    @Test
    public void joinMulti(){
        ChallengeUserVo challengeUserVo=new ChallengeUserVo(TEST_USER_NO+1,TEST_CL_NO, ChallengeUserStatus.READY);
        int result=challengeService.joinMulti(challengeUserVo);
        assertThat(result).isNotZero();
    }
    @Test
    public void challengeList(){
        List<ChallengeListVo> list1 = challengeService.getList(0);
        List<ChallengeListVo> list2 = challengeService.getList(1);
        assertThat(list1.size()).isNotZero();
        assertThat(list2.size()).isZero();
    }
    @Test
    public void clear(){
        int result=challengeService.clearTodo(new TodoClearVo(1212,TEST_CL_NO,TEST_USER_NO));
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void detail(){
        ChallengeDetailVo detail = challengeService.getDetail(3000);
        System.out.println(detail);
    }
}