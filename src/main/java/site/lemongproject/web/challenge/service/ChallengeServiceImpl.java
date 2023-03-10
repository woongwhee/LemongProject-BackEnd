package site.lemongproject.web.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.exception.IsNotWriterException;
import site.lemongproject.web.challenge.model.dao.ChallengeUserDao;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.todo.model.dao.HolidayDao;
import site.lemongproject.web.todo.model.dto.PeriodVo;
import site.lemongproject.web.todo.model.vo.OfficialHoliday;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.common.type.ChallengeUserStatus;
import site.lemongproject.web.challenge.model.dao.ChallengeChatDao;
import site.lemongproject.web.challenge.model.dao.ChallengeDao;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;
import site.lemongproject.web.challenge.model.dto.*;
import site.lemongproject.web.challenge.model.vo.*;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {
    final private ChallengeDao challengeDao;
    final private HolidayDao holidayDao;
    final private ChallengeChatDao chatDao;
    final private ChallengeTodoDao todoDao;
    final private ChallengeUserDao userDao;
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;

    /**
     * 멀티 챌린지를 참여
     *
     * @param joinVo
     * @return
     */
    @Override
    public int joinMulti(ChallengeUserVo joinVo) {
        joinVo.setStatus(ChallengeUserStatus.READY);
        int result = userDao.joinUser(joinVo);
        result *= todoDao.copyTodoList(joinVo);
        return result;
    }

    /**
     * 시작한 첼린지를 중도포기
     * 해당하는 challengeTodo를 전부 지우고,만약 챌린지에 남은 유저가 없다면
     *
     * @param
     * @return
     */
    @Override
    public int cancelMulti(ChallengeUserVo userVo) {
        Challenge challenge = challengeDao.findOne(userVo.getChallengeNo());
        int result = todoDao.deletePlay(userVo);
        int userCount = 0;
        if (challenge.getStatus() == ChallengeStatus.READY || challenge.getStatus() == ChallengeStatus.SINGLE) {
            result *= userDao.deleteUser(userVo);
            userCount = userDao.countPlayer(userVo.getChallengeNo());
        } else if (challenge.getStatus() == ChallengeStatus.PLAY) {
            result *= userDao.cancelUser(userVo);
        }
        if (result == 0) {
            return 0;
        }
        if (userCount == 0) {
            result *= challengeDao.cancelChallenge(userVo.getChallengeNo());
        }
        return result;
    }


    @Override
    public int createMulti(MultiCreateVo createVo) {
        createVo.setStatus(ChallengeStatus.READY);
        //첼린지의 기본적인 정보를 제목,옵션,시작일 등 Database에 넣는다 .
        int result = challengeDao.insertMulti(createVo);
        if (result == 0) {
            return 0;
        }
        EndDateUpdateVo updateVo = new EndDateUpdateVo();
        ChallengeUserVo userVo = new ChallengeUserVo(createVo.getUserNo(), createVo.getChallengeNo(), ChallengeUserStatus.READY);
        //작성자를 참여중인 유저에 넣는다.
        result *= userDao.joinUser(userVo);
        updateVo.setChallengeNo(createVo.getChallengeNo());
        CGTodoInsertVo insertVo = makeTodo(createVo, updateVo);
        System.out.println(updateVo);
        //임시저장용 Todo
        insertVo.setUserNo(-1);
        result *= todoDao.insertTodoList(insertVo);
        result *= todoDao.copyTodoList(userVo);
        result *= challengeDao.updateEndDate(updateVo);
        return result;
    }

    /**
     * 챌린지 시작 메소드 중복시작할수 없음
     *
     * @param startVo
     * @return
     */
    @Override
    public int startSingle(SingleStartVo startVo) {
        startVo.setStatus(ChallengeStatus.SINGLE);
        int result = challengeDao.insertSingle(startVo);
        if (result == 0) {
            return 0;
        }
        result *= userDao.joinUser(new ChallengeUserVo(startVo.getUserNo(), startVo.getChallengeNo(), ChallengeUserStatus.PLAY));
        EndDateUpdateVo updateVo = new EndDateUpdateVo();

        CGTodoInsertVo insertVo = makeTodo(startVo, updateVo);
        result *= todoDao.insertTodoList(insertVo);
        result *= challengeDao.updateEndDate(updateVo);
        //종료일을 설정한다.
        return result;
    }

    private CGTodoInsertVo makeTodo(SingleStartVo startVo, EndDateUpdateVo updateVo) {
        int templateNo = startVo.getTemplateNo();
        LocalDate startDate = startVo.getStartDate();
        ChallengeOption co = startVo.getOption();
        List<OfficialHoliday> holidayList = new ArrayList<>();
        if (co.isOfficialHoliday()) {
            holidayList = getOfficialHolidays(templateNo, startDate, co);
            System.out.println(holidayList);
        }
        List<TemplateTodo> todoList = templateTodoDao.findByTemplate(templateNo);
        List<CGTodoItemVo> challengeTodos = new ArrayList<>(todoList.size());
        int dayPoint = 1;
        LocalDate datePoint = startDate;
        int weekIndex = datePoint.getDayOfWeek().getValue() - 1;
        boolean[] optionArr = co.getOptionArray();
        for (TemplateTodo templateTodo : todoList) {
            loop:
            while (true) {
                if (templateTodo.getDay() != dayPoint) {
                    int period = templateTodo.getDay() - dayPoint;
                    dayPoint = templateTodo.getDay();
                    datePoint = datePoint.plusDays(period);
                    weekIndex = weekIndex + period % 7 > 6 ? weekIndex + period % 7 - 7 : weekIndex + period % 7;
                    if (!optionArr[weekIndex]) {
                        weekIndex = weekIndex < 6 ? weekIndex + 1 : 0;
                        datePoint = datePoint.plusDays(1);
                        continue;
                    }
                    for (OfficialHoliday officialHoliday : holidayList) {
                        LocalDate holiyDate = officialHoliday.getHoliday();
                        if (officialHoliday.getHoliday().isEqual(datePoint)) {
                        System.out.println(holiyDate.isEqual(datePoint) + "date" + datePoint);
                            weekIndex = weekIndex < 6 ? weekIndex + 1 : 0;
                            datePoint = datePoint.plusDays(1);
                            continue loop;
                        }
                    }
                }

                break;
            }
            CGTodoItemVo todo = new CGTodoItemVo();
            todo.setTodoDate(datePoint);
            todo.setTodoContent(templateTodo.getContent());
            todo.setValue(templateTodo.getValue());
            challengeTodos.add(todo);
        }
        CGTodoInsertVo insertVo = new CGTodoInsertVo();
        updateVo.setEndDate(datePoint);
        updateVo.setChallengeNo(startVo.getChallengeNo());
        insertVo.setTodoList(challengeTodos);
        insertVo.setUserNo(startVo.getUserNo());
        insertVo.setChallengeNo(startVo.getChallengeNo());
        return insertVo;
    }

    private List<OfficialHoliday> getOfficialHolidays(int templateNo, LocalDate startDate, ChallengeOption co) {
        List<OfficialHoliday> holidayList;
        int length = templateDao.findRange(templateNo);
        long range = (length / co.getWeekDo() + 1) * 7;
        LocalDate endDate = startDate.plusDays(range);
        PeriodVo periodVo = new PeriodVo(startDate, endDate);
        holidayList = holidayDao.findByPeriod(periodVo);
        return holidayList;
    }

    public Challenge selectChallenge(int challengeNo) {
        return challengeDao.findOne(challengeNo);
    }

    public ChallengeChat insertChatData(ChallengeChat chatData) {
        int result = chatDao.insertChatData(chatData);
        if (result == 0) {
            return null;
        }
        return chatDao.findOne(chatData.getChatNo());
    }


    public List<ChallengeTodo> calChTodo(ChallengeTodo ct) {
        return todoDao.calChTodo(ct);
    }

    @Override
    public List<ChallengeListVo> getList(int page) {
        return challengeDao.findReady(page, 8);
    }

    @Override
    public ChallengeDetailVo getDetail(int challengeNo) {
        return challengeDao.findDetail(challengeNo);
    }

    @Override
    public ChallengeRoomVo getRoomDetail(ChallengeUserVo userVo) {
        boolean inChallenge = userDao.inChallenge(userVo);
        if (!inChallenge) {
            throw new IsNotWriterException();
        }
        return challengeDao.findRoom(userVo.getChallengeNo());
    }

    /**
     * ChallengeTodo 상태를 반대로 바꾼다.
     * ChallengeUser의 ClearCount를 변경시킨다.
     *
     * @param clearVo
     * @return
     */
    @Override
    public int clearTodo(TodoClearVo clearVo) {
        int result = todoDao.clearChallengeTodo(clearVo);
        System.out.println(result);
        ChallengeTodo todo = todoDao.findOne(clearVo.getTodoNo());
        System.out.println(todo);
        result *= userDao.changeClear(todo);
        return result;
    }

    @Override
    public List<Challenge> detailChallenge(Challenge c) {
        return challengeDao.detailChallenge(c);
    }

    @Override
    public List<ChallengeListVo> profileChallengeList(int userNo) {
        return challengeDao.findRoomList(userNo);
    }
}
