package site.lemongproject.web.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lemongproject.common.domain.dao.HolidayDao;
import site.lemongproject.common.domain.dto.OfficialHoliday;
import site.lemongproject.common.domain.vo.PeriodVo;
import site.lemongproject.common.type.ChallengeStatus;
import site.lemongproject.web.challenge.model.dao.ChallengeChatDao;
import site.lemongproject.web.challenge.model.dao.ChallengeDao;
import site.lemongproject.web.challenge.model.dao.ChallengeTodoDao;
import site.lemongproject.web.challenge.model.dto.ChallengeOption;
import site.lemongproject.web.challenge.model.dto.ChallengeTodo;
import site.lemongproject.web.challenge.model.vo.EndDateUpdateVo;
import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.MultiJoinVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;
import site.lemongproject.web.template.model.dao.TemplateDao;
import site.lemongproject.web.template.model.dao.TemplateTodoDao;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    final private ChallengeDao challengeDao;
    final private HolidayDao holidayDao;
    final private ChallengeChatDao chatDao;
    final private ChallengeTodoDao todoDao;
    final private TemplateDao templateDao;
    final private TemplateTodoDao templateTodoDao;







    @Override
    public int createMulti(MultiCreateVo createVo) {
        createVo.setStatus(ChallengeStatus.READY);
        Template t = templateDao.findDetail(createVo.getTemplateNo());
        ChallengeOption co=createVo.getOption();
        long range = (t.getTodoCount() / co.getWeekDo() + 1) * 7;
        LocalDate startDate = createVo.getStartDate();
        List<OfficialHoliday> holidayList=null;
        LocalDate endDate = startDate.plusDays(range);
        //공휴일 제외인 경우
        if (co.isOfficialHoliday()) {
            PeriodVo periodVo = new PeriodVo(startDate, endDate);
            holidayDao.findByPeriod(periodVo);
        }
        int result=challengeDao.insertMulti(createVo);


        EndDateUpdateVo updateVo = new EndDateUpdateVo();
        updateVo.setChallengeNo(createVo.getChallengeNo());
        List<ChallengeTodo> challengeTodoList = parseMultiTodo(createVo, updateVo, holidayList, t.getTodoList());
        result*=todoDao.insertTodoList(challengeTodoList);
        result*=challengeDao.updateEndDate(updateVo);
        return result;

    }


    @Override
    public int joinMulti(MultiJoinVo msv) {
        return 0;

    }


    /**
     *
     * @param ssv
     * @return
     */
    @Override
    public int startSingle(SingleStartVo ssv) {
        ssv.setStatus(ChallengeStatus.SINGLE);
        Template t = templateDao.findDetail(ssv.getTemplateNo());
        ChallengeOption co=ssv.getOption();
        long range = (t.getTodoCount() / co.getWeekDo() + 1) * 7;
        LocalDate startDate = ssv.getStartDate();
        List<OfficialHoliday> holidayList=null;
        LocalDate endDate = startDate.plusDays(range);
        //공휴일 제외인 경우
        if (co.isOfficialHoliday()) {
            PeriodVo periodVo = new PeriodVo(startDate, endDate);
            holidayDao.findByPeriod(periodVo);
        }
        int result=challengeDao.insertSingle(ssv);


        EndDateUpdateVo updateVo = new EndDateUpdateVo();
        updateVo.setChallengeNo(ssv.getChallengeNo());
        List<ChallengeTodo> challengeTodoList = parseSingleTodo(ssv, updateVo, holidayList, t.getTodoList());
        result*=todoDao.insertTodoList(challengeTodoList);
        result*=challengeDao.updateEndDate(updateVo);




        return result;
    }

    private List<ChallengeTodo> parseSingleTodo(SingleStartVo startVo,EndDateUpdateVo updateVo, List<OfficialHoliday> holidayList, List<TemplateTodo> tpTodos) {

        List<ChallengeTodo> challengeTodos=new ArrayList<>(tpTodos.size());
        int dayPoint = 1;
        LocalDate datePoint = startVo.getStartDate();
        int weekIndex = datePoint.getDayOfWeek().getValue() - 1;
        boolean[] optionArr = startVo.getOption().getOptionArray();
        Iterator<OfficialHoliday> iter=holidayList.iterator();
        for (TemplateTodo templateTodo : tpTodos) {

            if (templateTodo.getDay() != dayPoint) {
                //해당일이 수행일인지 확인
                dayPoint = templateTodo.getDay();
                int period = templateTodo.getDay() - dayPoint;
                datePoint = datePoint.plusDays(period);
                weekIndex = weekIndex + period % 7 > 6 ? weekIndex + period % 7 - 7 : weekIndex + period % 7;
                loop:while(true){
                    if(!optionArr[weekIndex]){
                        weekIndex=weekIndex < 6 ? weekIndex + 1 :0;
                        datePoint=datePoint.plusDays(1);
                        continue;
                    }
                    while(iter.hasNext()) {
                        LocalDate holiyDate=iter.next().getHoliday();
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
            ChallengeTodo todo=new ChallengeTodo();
            todo.setChallengeNo(startVo.getChallengeNo());
            todo.setUserNo(startVo.getUserNo());
            todo.setTodoDate(datePoint);
            todo.setTodoContent(templateTodo.getContent());
            todo.setValue(templateTodo.getValue());
            challengeTodos.add(todo);
        }
        updateVo.setEndDate(datePoint);
        return challengeTodos;
    }

    /**
     * 복사용 데이터를 생성함
     * @param createVo
     * @param updateVo
     * @param holidayList
     * @param tpTodos
     * @return
     */
    private List<ChallengeTodo> parseMultiTodo(MultiCreateVo createVo,EndDateUpdateVo updateVo, List<OfficialHoliday> holidayList, List<TemplateTodo> tpTodos) {
        List<ChallengeTodo> challengeTodos=new ArrayList<>(tpTodos.size());

        int dayPoint = 1;
        LocalDate datePoint = createVo.getStartDate();
        int weekIndex = datePoint.getDayOfWeek().getValue() - 1;
        boolean[] optionArr = createVo.getOption().getOptionArray();
        Iterator<OfficialHoliday> iter=holidayList.iterator();
        for (TemplateTodo templateTodo : tpTodos) {

            if (templateTodo.getDay() != dayPoint) {
                //해당일이 수행일인지 확인
                dayPoint = templateTodo.getDay();
                int period = templateTodo.getDay() - dayPoint;
                datePoint = datePoint.plusDays(period);
                weekIndex = weekIndex + period % 7 > 6 ? weekIndex + period % 7 - 7 : weekIndex + period % 7;
                loop:while(true){
                    if(!optionArr[weekIndex]){
                        weekIndex=weekIndex < 6 ? weekIndex + 1 :0;
                        datePoint=datePoint.plusDays(1);
                        continue;
                    }
                    while(iter.hasNext()) {
                        LocalDate holiyDate=iter.next().getHoliday();
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
            ChallengeTodo todo=new ChallengeTodo();
            todo.setChallengeNo(createVo.getChallengeNo());
            todo.setUserNo(-1);
            todo.setTodoDate(datePoint);
            todo.setTodoContent(templateTodo.getContent());
            todo.setValue(templateTodo.getValue());
            challengeTodos.add(todo);
        }
        updateVo.setEndDate(datePoint);
        return challengeTodos;
    }


}
