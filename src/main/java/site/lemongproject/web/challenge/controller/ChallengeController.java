package site.lemongproject.web.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.lemongproject.web.challenge.service.ChallengeService;

@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    final private ChallengeService challengeService;




}
