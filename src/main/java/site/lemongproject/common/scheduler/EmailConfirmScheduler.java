package site.lemongproject.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.member.model.dao.EmailConfirmDao;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConfirmScheduler {

    final private EmailConfirmDao emailConfirmDao;

    // 매주 화요일 오후 7시에 자동으로 삭제
    @Scheduled(cron = "0 0 19 ? * TUE", zone = "Asia/Seoul")

    // 샘플 테스트(3분마다 삭제)
//    @Scheduled(cron = "0 0/3 * 1/1 * ?", zone = "Asia/Seoul")

    @Transactional
    public void deleteEmailConfirm() {
        log.info("일주일(임시) 지난 이메일 삭제 시작");

        int emailDelete = emailConfirmDao.deleteEmail();
        log.info("삭제된 인증번호: "+emailDelete);

        log.info("삭제 완료");

    }



}
