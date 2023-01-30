package site.lemongproject.web.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.follow.model.service.FollowService;
import site.lemongproject.web.follow.model.vo.Follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    final private FollowService followService;

    // 팔로워 신청
    // 팔로우 당하는사람(팔로워) => follower (params에서 뽑아온 userNo)
    // 팔로우 하는사람(팔로잉) => followerIng (sessiong에서 뽑아온 userNo)
    @GetMapping("/followGo")
    public ResponseBody<Follow> insertFollow(
            @RequestParam(value = "follower" , required = false) int follower ,
            @RequestParam(value = "followerIng" , required = false) int followerIng){

        System.out.println(follower);
        System.out.println(followerIng);

        Follow f = new Follow();

        f.setFollower(follower);
        f.setFollowing(followerIng);

        int result = followService.insertFollow(f);

        return ResponseBuilder.success(result);

    }

    // 나한테 온 팔로워 리스트 보여주기.
    @GetMapping("/MyfollowerList")
    public ResponseBody<Follow> selectMyFollowerList(
            @RequestParam(value = "followerNo" , required = false) int followerNo ,
            @RequestParam(value = "followerIngNo" , required = false) int followerIngNo){

        Follow f = new Follow();
        f.setFollower(followerNo);
        f.setFollowing(followerIngNo);

        List<Follow> fList = followService.selectMyFollowerList(f);

        return ResponseBuilder.success(fList);
    }

    // 팔로우 신청 수락.
    @GetMapping("/followOk")
    public ResponseBody<Follow> updateFollowOk(
            @RequestParam(value="followerIng" , required = false) int followerIng ,
            @RequestParam(value="follower" , required = false) int follower){

        System.out.println(followerIng + " : ok");
        System.out.println(follower + " : ok");

        Follow f = new Follow();
        f.setFollowing(followerIng);
        f.setFollower(follower);

        int result = followService.updateFollowOk(f);

        return ResponseBuilder.success(result);
    }

    // 팔로우 신청 취소
    @GetMapping("/followDelete")
    public ResponseBody<Follow> followDelete(
            @RequestParam(value="follower" , required = false) int follower ,
            @RequestParam(value="followerIng" , required = false) int followerIng){

        Follow f = new Follow();
        f.setFollower(follower);
        f.setFollowing(followerIng);

        int result = followService.followDelete(f);

        return ResponseBuilder.success(result);
    }

    // 로그인한 유저 입장에서 팔로우 신청 시 상대방 수락여부에 상관없이 팔로워 카운트 올라가야함.
    @GetMapping("/MyFollowCount")
    public ResponseBody<Follow> MyFollowCount(
            @RequestParam(value = "followerIng" , required = false) int followerIng){

        Follow f = new Follow();
        f.setFollowing(followerIng);

        Follow fcount = followService.MyFollowCount(f);

        return ResponseBuilder.success(fcount);

    }

    // 팔로우 신청을 받은 유저 입장에서 내가 팔로우 신청을 수락 시 나의 팔로워 카운트가 올라가야함.
    @GetMapping("/AcceptFollowCount")
    public ResponseBody<Follow> AcceptFollowCount(
            @RequestParam(value = "follower" , required = false) int follower ,
            @RequestParam(value = "followerIng" , required = false) int followerIng){

        Follow f = new Follow();
        f.setFollower(follower);
        f.setFollowing(followerIng);

        Follow fcount = followService.AcceptFollowCount(f);

        return ResponseBuilder.success(fcount);

    }

    // 팔로우 신청을 받은 유저 입장에서 나의 팔로워신청 수락여부에 상관없이 팔로잉 숫자가 증가되어야함.
    @GetMapping("/AcceptFollowingCount")
    public ResponseBody<Follow> AcceptFollowingCount(
            @RequestParam(value = "follower" , required = false) int follower){

        Follow f = new Follow();
        f.setFollower(follower);

        Follow fingCount = followService.AcceptFollowingCount(f);

        return ResponseBuilder.success(fingCount);
    }

    // 내가 팔로우 하고 있는 팔로우리스트 띄우기.
    @GetMapping("/selectMyFollowerList")
    public ResponseBody<Follow> selectMyFollowerList(
            @RequestParam(value = "followerIng" , required = false) int followerIng){

        Follow f = new Follow();
        f.setFollowing(followerIng);

        List<Follow> fmyList = followService.selectMyFollowersdList(f);

        return ResponseBuilder.success(fmyList);
    }

}
