package site.lemongproject.web.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
     *
     * @param
     * @return
     */
    @Override
    public int leaveMulti(ChallengeUserVo userVo) {
        int result = challengeDao.deleteUser(userVo);
        return result;
    }


    @Override
    public int createMulti(MultiCreateVo createVo) {
        createVo.setStatus(ChallengeStatus.READY);
        int result = challengeDao.insertMulti(createVo);
        if (result == 0) {
            return 0;
        }
        EndDateUpdateVo updateVo = new EndDateUpdateVo();
        //방장도 challengeUser에 넣는다.
        ChallengeUserVo userVo = new ChallengeUserVo(createVo.getUserNo(), createVo.getChallengeNo(), ChallengeUserStatus.READY);
        result *= userDao.joinUser(userVo);
        updateVo.setChallengeNo(createVo.getChallengeNo());
        CGTodoInsertVo insertVo = makeTodo(createVo, updateVo);
        System.out.println(updateVo);
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
        result *= userDao.joinUser(new ChallengeUserVo(startVo.getUserNo(), startVo.getChallengeNo(), ChallengeUserStatus.READY));
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
        //공휴일 제외인경우
        if (co.isOfficialHoliday()) {
            holidayList = getOfficialHolidays(templateNo, startDate, co);
        }
        List<TemplateTodo> todoList = templateTodoDao.findByTemplate(templateNo);
        List<CGTodoItemVo> challengeTodos = new ArrayList<>(todoList.size());
        int dayPoint = 1;
        LocalDate datePoint = startDate;
        int weekIndex = datePoint.getDayOfWeek().getValue() - 1;
        boolean[] optionArr = co.getOptionArray();
        Iterator<OfficialHoliday> iter = holidayList.iterator();
        for (TemplateTodo templateTodo : todoList) {
            if (templateTodo.getDay() != dayPoint) {
                //해당일이 수행일인지 확인
                int period = templateTodo.getDay() - dayPoint;
                dayPoint = templateTodo.getDay();
                datePoint = datePoint.plusDays(period);
                weekIndex = weekIndex + period % 7 > 6 ? weekIndex + period % 7 - 7 : weekIndex + period % 7;
                loop:
                while (true) {
                    //해당 요일이 수행일인지 확인
                    if (!optionArr[weekIndex]) {
                        weekIndex = weekIndex < 6 ? weekIndex + 1 : 0;
                        datePoint = datePoint.plusDays(1);
                        continue;
                    }
                    //해당일이 공휴일인지;(공휴일제외를 하지 않았을시 iter의 값이 비어있음)
                    while (iter.hasNext()) {
                        LocalDate holiyDate = iter.next().getHoliday();
                        if (holiyDate.equals(datePoint)) {
                            weekIndex = weekIndex < 6 ? weekIndex + 1 : 0;
                            datePoint = datePoint.plusDays(1);
                            continue loop;
                        }
                        if (holiyDate.isBefore(datePoint)) {
                            iter.remove();
                        }
                    }

                    break;
                }
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

    public int insertChatData(ChallengeChat chatData) {
        return chatDao.insertChatData(chatData);
    }


    public List<ChallengeTodo> calChTodo(ChallengeTodo ct) {
        return todoDao.calChTodo(ct);
    }

    @Override
    public List<ChallengeListVo> getList(int page) {
        return challengeDao.findReady(page, 8);
    }
    @Override
    public ChallengeDetailVo getDetail(int challengeNo){
        return challengeDao.findDetail(challengeNo);
    }
    @Override
    public int clearTodo(TodoClearVo clearVo) {
        int result = todoDao.clearChallengeTodo(clearVo);
        System.out.println(result);
        ChallengeTodo todo = todoDao.findOne(clearVo.getTodoNo());
        System.out.println(todo);
        result *= userDao.changeClear(todo);
        return result;
    }

    public List<Challenge> detailChallenge(Challenge c) {
        return challengeDao.detailChallenge(c);
    }

    public int challengeGo(ChallengeUser u) {
        return challengeDao.challengeGo(u);
    }
}
