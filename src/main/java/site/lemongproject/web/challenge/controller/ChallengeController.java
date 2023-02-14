package site.lemongproject.web.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.model.vo.*;
import site.lemongproject.web.challenge.service.ChallengeService;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.todo.model.vo.Todo;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    final private ChallengeService challengeService;

    // 테스트 챌린지No(3000)번에 해당하는 정보 다가져오기.
    @GetMapping("/selectChallenge")
    public ResponseBody<Challenge> selectChallenge(@RequestParam(value = "challNo", required = false) int challNo) {
        Challenge c = challengeService.selectChallenge(challNo);
        return ResponseBuilder.success(c);

    }

    @PostMapping("/start/single")
    public ResponseBody<Challenge> startSingle(@SessionAttribute("loginUser") Profile loginUser, @RequestBody SingleStartVo startVo) {
        System.out.println(startVo);
        startVo.setUserNo(loginUser.getUserNo());
        int result = challengeService.startSingle(startVo);
        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.serverError();
        }

    }

    @PostMapping("/start/multi")
    public ResponseBody<Challenge> startMulti(@SessionAttribute("loginUser") Profile loginUser, @RequestBody MultiCreateVo createVo) {
        System.out.println(createVo);
        createVo.setUserNo(loginUser.getUserNo());
        int result = challengeService.createMulti(createVo);
        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.serverError();
        }
    }

    // challNo에 해당하는 챌린지 상세정보 가져오기.
    @GetMapping("/detailChallenge")
    public ResponseBody<Challenge> detailChallenge(@RequestParam(value = "challNo", required = false) int challNo) {
        Challenge c = new Challenge();
        c.setChallengeNo(challNo);

        List<Challenge> cOne = challengeService.detailChallenge(c);
        return ResponseBuilder.success(cOne);
    }

    // 챌린지 참여하기 버튼 클릭 시 ready상태로 insert됨 -> 그 후 챌린지 시작하기 버튼 클릭 시 play상태로 변경.
    // ready상태에서도 채팅방 사용 가능.

    @GetMapping("/list/ready/{page}")
    public ResponseBody<ChallengeListVo> readyList(@PathVariable("page") int page) {
        List<ChallengeListVo> list = challengeService.getList(page);

        if (list == null || list.size() == 0) {
            return ResponseBuilder.findNothing();
        } else {
            return ResponseBuilder.success(list);
        }

    }
    @GetMapping("/detail/ready/{challengeNo}")
    public ResponseBody<ChallengeDetailVo> readyDetail(@PathVariable("challengeNo") int challengeNo) {
        ChallengeDetailVo detail = challengeService.getDetail(challengeNo);
        if (detail==null) {
            return ResponseBuilder.findNothing();
        }

        return ResponseBuilder.success(detail);


    }
    @GetMapping("/clearTodo")
    public ResponseBody<Todo> clearTodo(@RequestParam(value = "todoNo", required = false) long todoNo, @RequestParam(value = "templateNo", required = false) int templateNo, @SessionAttribute("loginUser") Profile loginUser) {
        int result = challengeService.clearTodo(new TodoClearVo(todoNo, templateNo, loginUser.getUserNo()));
        if (result > 0) {
            return ResponseBuilder.success(result);
        } else {
            return ResponseBuilder.upLoadFail();
        }
    }

    @GetMapping("/challengeGo")
    public ResponseBody<ChallengeUser> challengeGo(@RequestParam(value = "challNo", required = false) int challNo,
                                                   @RequestParam(value = "userNo", required = false) int userNo) {

        ChallengeUser u = new ChallengeUser();
        u.setChallengeNo(challNo);
        u.setUserNo(userNo);

        int result = challengeService.challengeGo(u);

        return ResponseBuilder.success(result);
    }

}
