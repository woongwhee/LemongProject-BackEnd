package site.lemongproject.web.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.web.follow.model.service.FollowService;
import site.lemongproject.web.follow.model.vo.Follow;
import site.lemongproject.web.member.model.vo.Profile;

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
    @GetMapping("/followGo/{follow}/{followIng}")
    public ResponseBody<Follow> insertFollow(
            @PathVariable(value = "follow" , required = false) int follow ,
            @PathVariable(value = "followIng" , required = false) int followIng){
        Follow f = new Follow();
        f.setFollower(follow);
        f.setFollowing(followIng);
        int result = followService.insertFollow(f);

        return ResponseBuilder.success(result);

    }

    // 나한테 온 팔로워 리스트 보여주기.
    @GetMapping("/MyfollowerList/{userNo}")
    public ResponseBody<Follow> selectMyFollowerAlertList(
            @RequestParam(value = "userNo" , required = false) int follower){
        Follow f = new Follow();
        f.setFollower(follower);
        List<Follow> fList = followService.selectMyFollowerAlertList(f);
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
    @GetMapping("/MyFollowCount/{followerIng}")
    public ResponseBody<Integer> MyFollowCount(
            @PathVariable(value = "followerIng" , required = false) int followerIng){
        Follow f = new Follow();
        f.setFollowing(followerIng);
        int fcount = followService.MyFollowCount(f);

        return ResponseBuilder.success(fcount);

    }

    // 팔로우 신청을 받은 유저 입장에서 내가 팔로우 신청을 수락 시 나의 팔로워 카운트가 올라가야함.
    @GetMapping("/AcceptFollowCount")
    public ResponseBody<Follow> AcceptFollowCount(
            @RequestParam(value = "followerIng" , required = false) int followerIng){

        Follow f = new Follow();
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

    @GetMapping("/selectMyFollowingList/{follower}")
    public ResponseBody<Follow> selectMyFollowingList(
            @PathVariable(value = "follower" , required = false) int follower){

        Follow f = new Follow();
        f.setFollower(follower);
        List<Follow> fmyList = followService.selectMyFollowingList(f);

        return ResponseBuilder.success(fmyList);
    }
    // 내가 팔로우 하고 있는 팔로우리스트 띄우기.
    @GetMapping("/selectMyFollowerList/{userNo}")
    public ResponseBody<Follow> selectMyFollowerList(
            @PathVariable("userNo") int userNo){
        Follow f = new Follow();
        f.setFollowing(userNo);
        List<Follow> fmyList = followService.selectMyFollowersdList(f);
        return ResponseBuilder.success(fmyList);
    }

    // 로그인한 유저 입장에서 나의 수락 여부에 상관없이 팔로잉이 늘어나야함.
    @GetMapping("/MyFollowingCount")
    public ResponseBody<Integer> MyFollowingCount(@RequestParam(value = "follower") int follower){

        Follow f = new Follow();
        f.setFollower(follower);
        int fcount = followService.MyFollowingCount(f);

        return ResponseBuilder.success(fcount);
    }

    // 팔로우 신청 받은 사용자 입장에서 팔로우 신청 수락 여부에 상관없이 팔로잉 숫자가 증가해야함.
    @GetMapping("/selectAcceptFollowingList")
    public ResponseBody<Follow> selectAcceptFollowingList(@RequestParam(value = "follower" , required = false) int follower){

        Follow f = new Follow();
        f.setFollower(follower);

        List<Follow> fingList = followService.selectAcceptFollowingList(f);

        return ResponseBuilder.success(fingList);

    }

    // 팔로우 신청을 받은 사용자 입장에서 팔로워 신청 수락 시 팔로워에 포함됨.
    @GetMapping("/selectAcceptFollowerList")
    public ResponseBody<Follow> selectAcceptFollowerList(@RequestParam(value = "followerIng" , required = false) int followerIng){

        Follow f = new Follow();
        f.setFollowing(followerIng);

        List<Follow> ferList = followService.selectAcceptFollowerList(f);

        return ResponseBuilder.success(ferList);

    }

    // 나를 팔로우 하고 있는 팔로우리스트 띄우기.
}
