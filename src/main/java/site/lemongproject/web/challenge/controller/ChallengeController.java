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
import site.lemongproject.web.challenge.service.ChallengeService;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    final private ChallengeService challengeService;

    // 테스트 챌린지No(3000)번에 해당하는 정보 다가져오기.
    @GetMapping("/selectChallenge")
    public ResponseBody<Challenge> selectChallenge(@RequestParam(value = "challNo" , required = false)int challNo){

       Challenge cNo = new Challenge();
       cNo.setChallengeNo(challNo);

       Challenge c = challengeService.selectChallenge(cNo);

        return ResponseBuilder.success(c);

    }






}
