package site.lemongproject.web.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;
import site.lemongproject.common.type.SocialType;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.service.MemberService;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.vo.Photo;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;
    final private FileUtil fileUtil;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;


    // 로그인


//     마이페이지 회원정보 수정
////
////    // MEMBER테이블 유저 정보 조회.

    // USER_PROFILE테이블 유저 닉네임 업데이트.
    @GetMapping("/checkNickName")
    public int updateUser(@SessionAttribute("loginUser")Member loginUser,@RequestParam(value="modifyNickname" , required=false) String nickName){
        Profile p=new Profile();
        p.setUserNo(loginUser.getUserNo());
        p.setNickName(nickName);
        int upList = memberService.updateProfile(p);

        System.out.println(nickName);

        return upList;
    }

    // USER_PROFILE테이블 유저 자기소개 업데이트.
    @GetMapping("/updateComment")
    public int updateComment(@SessionAttribute("loginUser")Member loginUser,@RequestParam(value="modifyComment" , required = false) String comment){
        Profile p=new Profile();
        p.setUserNo(loginUser.getUserNo());
        p.setProfileComment(comment);
        int upList2 = memberService.updateProfile(p);
        return upList2;
    }

    @GetMapping("/myPwdUpdate")
    public int myupdatePwd(@SessionAttribute("loginUser") Member loginUser , @RequestParam(value = "updatePwd" , required = false)String updatePwd){
        String pwd = bCryptPasswordEncoder.encode(updatePwd);
        int userNo = loginUser.getUserNo();

        System.out.println(updatePwd + " === success === ");

        ChangePwdVo cpw = new ChangePwdVo(userNo,pwd);
        int result = memberService.updatePassword(cpw);

        return result;
    }

    // 유저 프로필 INSERT. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @PostMapping("/insertUserProfile")
//    @RequestMapping(value="/insertUserProfile", method=RequestMethod.POST)
    public ResponseBody<Photo> insertProfilePhoto(
            @RequestParam(value="file", required=false) MultipartFile[] files,
            @SessionAttribute("loginUser")Member loginUser) {
        Photo p = new Photo();
        p.setUserNo(loginUser.getUserNo());
        fileUtil.saveFile(files[0], p);
        int result = memberService.insertUserPhoto(p);
        return ResponseBuilder.success(p);

    }
    // 유저 프로필 UPDATE. => 웅휘형이 만든 FileUtil로 빼기 => rename(m.getOriginalFilename()) 오류 고치기.
    @RequestMapping(value = "/updateUserProfile")
    public int updateUserProfile(
            @RequestParam(value = "file" , required = false) MultipartFile[] ufiles ,
            @SessionAttribute(value = "loginMember") Member loginMember) {

//        String webPath = "/resources/images/userProfile/";
//
//        int result = 0;
//
//        Photo p = new Photo();
//
//        for (MultipartFile mf : ufiles) {
//
//            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
//            long fileSize = mf.getSize(); // 파일 사이즈
//            System.out.println("originFileName : " + originFileName);
//            System.out.println("fileSize : " + fileSize);
//
//            // 1. 원본 파일명 뽑기.
//            String originName= mf.getOriginalFilename();
//
//            // 2. 시간 형식을 문자열로 뽑아오기.
//            // 년월일시분초
//            String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//            // 3. 뒤에 붙을 5자리 랜덤값 뽑기.
//            int random = (int)(Math.random() * 90000 + 10000); // 5자리 랜덤값
//
//            // 4. 원본파일명으로부터 확장자명 뽑기.
//            // .jpg
//            String ext = originName.substring(originName.lastIndexOf("."));
//
//            // 5. 다 이어붙이기.
//            String changeName = currentTime + random + ext;
//
//            // 폴더에 이미지 저장.
//            String savePath = "C:/LemongProject/src/main/webapp/resources/images/userProfile/";
//
//            if (!mf.getOriginalFilename().equals("")) { // 파일명이 비어있지 않은 경우
//                try {
//                    mf.transferTo(new File(savePath + changeName));
//                } catch (IllegalStateException | IOException e) {
//                    System.out.println(e.getMessage() + "오류 발생");
//                }
//            }
//
//            p.setFilePath(webPath+mf.getOriginalFilename());
//            p.setUserNo(userNo);
//            p.setChangeName(changeName);
//            p.setOriginName(mf.getOriginalFilename());
//
//            result = memberService.updateUserProfile(p);
//
//        }
        Photo p=new Photo();
        p.setUserNo(loginMember.getUserNo());
        fileUtil.saveFile(ufiles[0],p );
        int result=memberService.insertUserPhoto(p);


        return result;

    }

    @GetMapping("/myprofile")
    public ResponseBody<MyProfileVo> getLogin(@SessionAttribute("loginUser") Member loginMember){

//        List<Member> mList = memberService.userSelect(loginMember);
        MyProfileVo glv=memberService.getMyProfile(loginMember.getUserNo());
        if (glv!=null) {
            return ResponseBuilder.success(glv);
        }else
        {
            return ResponseBuilder.success(glv);
        }
    }
    //

    @GetMapping("/selectMember")
    public ResponseBody<Member> selectMembers(@RequestParam(value = "userNo") int userNo){

        Member m = memberService.selectMembers(userNo);
        return ResponseBuilder.success(m);
    }

    // userNo에 해당하는 user 프로필 정보 가져오기(changeName 포함).
    @GetMapping("/selectMyProfile")
    public ResponseBody<Profile> selectMyProfile(@RequestParam(value = "userNo" , required = false) int userNo){
        Profile p = memberService.selectMyProfile(userNo);
        return ResponseBuilder.success(p);
    }

    // 검색 기능(유저 아이디 검색)
    @GetMapping("/searchUser")
    public ResponseBody<Profile> searchUser(@RequestParam(value="userNick" , required = false) String userNick){

        System.out.println(userNick + " : success");

        List<Profile> p = memberService.searchUser(userNick);

        System.out.println(p + " : success");

        return ResponseBuilder.success(p);
    }
    @GetMapping("/logout")
    public ResponseBody<Integer> logout(@SessionAttribute("loginUser")Profile profile, @SessionAttribute("socialType")SocialType socialType,HttpSession session){
        switch (socialType){
            case NONE: session.invalidate();
            case NAVER:
            case KAKAO:
        }
        return ResponseBuilder.success(11);
    }
        // 유효성 검사 통과 후 닉네임 변경.
        @GetMapping("/updateMyNick")
        public ResponseBody<Profile> updateMyNick(@RequestParam(value = "updateNick" , required = false) String updateNick ,
        @RequestParam(value = "userNo" , required = false) int userNo){

            System.out.println(userNo + " === updateMyNick === ");
            System.out.println(updateNick + " === updateMyNick === ");

            Profile pro = new Profile();
            pro.setUserNo(userNo);
            pro.setNickName(updateNick);

            Profile p = memberService.updateMyNick(pro);

            return ResponseBuilder.success(p);
        }

        // 마이페이지 자기소개 변경
        @GetMapping("/updateMyContent")
        public ResponseBody<Profile> updateMyContent(@RequestParam(value = "updateCont" , required = false) String updateCont ,
        @RequestParam(value = "userNo" , required = false) int userNo){
            Profile p = new Profile();
            p.setProfileComment(updateCont);
            p.setUserNo(userNo);

            Profile content = memberService.updateMyContent(p);

            return ResponseBuilder.success(content);
        }



}