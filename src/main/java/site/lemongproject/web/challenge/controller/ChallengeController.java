package site.lemongproject.web.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.challenge.model.dto.Challenge;
import site.lemongproject.web.challenge.model.dto.ChallengeUser;
import site.lemongproject.web.challenge.service.ChallengeService;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    final private ChallengeService challengeService;

    // 테스트 챌린지No(3000)번에 해당하는 정보 다가져오기.
    @GetMapping("/selectChallenge")
    public ResponseBody<Challenge> selectChallenge(){

        Challenge cNo = new Challenge();

        List<Challenge> c = challengeService.selectChallenge();

        return ResponseBuilder.success(c);

    }

    // challNo에 해당하는 챌린지 상세정보 가져오기.
    @GetMapping("/detailChallenge")
    public ResponseBody<Challenge> detailChallenge(@RequestParam(value = "challNo" , required = false)int challNo){
        Challenge c = new Challenge();
        c.setChallengeNo(challNo);

        List<Challenge> cOne = challengeService.detailChallenge(c);

        return ResponseBuilder.success(cOne);
    }

    // 챌린지 참여하기 버튼 클릭 시 ready상태로 insert됨 -> 그 후 챌린지 시작하기 버튼 클릭 시 play상태로 변경.
    // ready상태에서도 채팅방 사용 가능.
    @GetMapping("/challengeGo")
    public ResponseBody<ChallengeUser> challengeGo(@RequestParam(value = "challNo" , required = false) int challNo ,
                                                   @RequestParam(value = "userNo" , required = false) int userNo){

        ChallengeUser u = new ChallengeUser();
        u.setChallengeNo(challNo);
        u.setUserNo(userNo);

        int result = challengeService.challengeGo(u);

        return ResponseBuilder.success(result);
    }

}
