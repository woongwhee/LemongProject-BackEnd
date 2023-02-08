package site.lemongproject.web.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.vo.MultiCreateVo;
import site.lemongproject.web.challenge.model.vo.SingleStartVo;
import site.lemongproject.web.challenge.service.ChallengeService;
import site.lemongproject.web.member.model.vo.Profile;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    final private ChallengeService challengeService;

    // 테스트 챌린지No(3000)번에 해당하는 정보 다가져오기.
    @GetMapping("/selectChallenge")
    public ResponseBody<Challenge> selectChallenge(@RequestParam(value = "challNo", required = false) int challNo) {

        Challenge cNo = new Challenge();
        cNo.setChallengeNo(challNo);

        Challenge c = challengeService.selectChallenge(cNo);

        return ResponseBuilder.success(c);

    }

    @PostMapping("/start/single")
    public ResponseBody<Challenge> startSingle(@SessionAttribute("loginUser")Profile loginUser, @RequestBody SingleStartVo startVo) {
        System.out.println(startVo);
        startVo.setUserNo(loginUser.getUserNo());
        int result = challengeService.startSingle(startVo);
        if (result>0) {
            return ResponseBuilder.success(result);
        }else {
            return ResponseBuilder.serverError();
        }

    }

    @PostMapping("/start/multi")
    public ResponseBody<Challenge> startMulti(@SessionAttribute("loginUser")Profile loginUser, @RequestBody MultiCreateVo createVo) {
        System.out.println(createVo);
        createVo.setUserNo(loginUser.getUserNo());
        int result = challengeService.createMulti(createVo);
        if (result>0) {
            return ResponseBuilder.success(result);
        }else {
            return ResponseBuilder.serverError();
        }

    }


}
