package site.lemongproject.web.member.model.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.util.FileUtil;
import site.lemongproject.web.member.model.dao.EmailConfirmDao;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.dto.JoinVo;
import site.lemongproject.web.member.model.vo.EmailConfirm;
import site.lemongproject.web.member.model.dao.ProfileDao;
import site.lemongproject.web.member.model.dto.MyProfileVo;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.member.model.vo.Profile;
import site.lemongproject.web.photo.model.dao.PhotoDao;
import site.lemongproject.web.photo.model.vo.Photo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final private MemberDao memberDao;
    final private ProfileDao profileDao;
    final private PhotoDao photoDao;
    final private EmailConfirmDao confirmDao;
    final private FileUtil fileUtil;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Profile loginMember(Member m) {
        Member loginUser = memberDao.loginMember(m);
        if(bCryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())){
            return profileDao.findOne(loginUser.getUserNo());

        }else{
            return null;
        }
    }


    @Override
//    public int insertMember(Map<String, Object> m) {
    public int insertMember(JoinVo joinVo) {
//        int result = memberDao.insertMember(m);
        int result = memberDao.insertMember(joinVo);
        result *= profileDao.createProfile(joinVo.getNickName());
//        System.out.println("회원가입 dao 실행 : " + result);
//        result*=profileDao.createProfile((String) m.get("nickName"));
        return result;
    }


    @Override
    public int isExEmail(String email) {
        int exEmail = confirmDao.isExEmail(email);
        System.out.println("회원 이메일 중복체크 : "+exEmail);
        return exEmail;
    }


    @Override
    public int insertConfirm(EmailConfirm confirm) {
        confirmDao.deleteAnother(confirm);
        int result = confirmDao.insertConfirm(confirm);
        return result;
    }

    @Override
    public int checkEmail(EmailConfirm confirm) {
        return confirmDao.checkConfirm(confirm);
    }

//    public int insertMember(Member m) {
//        int result = memberDao.insertMember(m);
//        System.out.println("dao 실행 : " + result);
//        return result;
//    }


    @Override
    public int checkNick(String nickName) {
        int result = profileDao.checkNick(nickName);
        System.out.println("중복 체크 dao 실행: " + result);
        return result;
    }


    // 카카오 로그인 access token 발급
    @Override
    public String getAccessToken(String authCode) {
        String acessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        // access token을 받기 위한 중요 코드 -> 나중에 따로 빼기
        String restApi = "6c1bbd8efca92b427aff16845e3336d1";
        String redirectUri = "http://localhost:3000/kakao";
//        String redirectUri = "http://lemongproject.site/kakao";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HttpURLConnection 설정 값 셋팅
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // buffer를 통해 값 셋팅 후 요청
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+restApi);
            sb.append("&redirect_uri="+redirectUri);
            sb.append("&code="+authCode);
            bw.write(sb.toString());
            bw.flush();


            // return 값을 새로운 변수에 저장
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String brLine = "";
            String result = "";

            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            // 토근 값 저장 및 리턴
            acessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return acessToken;
    }


    // 카카오 로그인 접속자 정보 get
    @Override
    public Map<String, Object> getKakaoUser(String token) {
        Map<String, Object> kakaoUser = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함할 내용
            conn.setRequestProperty("Authorization", "Bearer "+token);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode: "+responseCode);
            // 실행 문제 없음

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String brLine = "";
            String result = "";

            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            System.out.println("element: "+element);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickName = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

            kakaoUser.put("nickName", nickName);
            kakaoUser.put("email", email);

            br.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return kakaoUser;
    }


    // 소셜 회원인지 확인
    @Override
    public Member isSocialUser(Member isSocial) {
        Member result = memberDao.isSocialUser(isSocial);
//        System.out.println("소셜유저 서비스: "+result);
        return result;
    }

    @Override
    public Profile socialProfile(Member isSocial) {
        Member socialUser = memberDao.isSocialUser(isSocial);
        Profile socialProfile = profileDao.findOne(socialUser.getUserNo());
        System.out.println("소셜유저 프로필 서비스:" +socialProfile);
        return socialProfile;
    }



    // 소셜 회원 회원가입
    @Override
    public int insertSocial(Member isSocial) {
        int result = memberDao.insertSocial(isSocial);
        System.out.println("소셜유저 삽입: "+result);
        return result;
    }


    // DB에 저장되어 있는 카카오 회원인지 확인
//    @Override
//    public Member isKakaoUser(Member isKakao) {
//        Member result = memberDao.isSocialUser(isKakao);
//        System.out.println("kakaoUser 서비스: "+result);
//        return result;
//    }


    // 카카오 회원 가입
//    @Override
//    public int insertKakao(Member isKakao) {
//        int result = memberDao.insertSocial(isKakao);
//        System.out.println("kakaoUser 삽입: "+result);
//        return result;
//    }

    // 소셜 로그인 사용자 닉네임 셋팅
    @Override
    public int setNick(JoinVo newMem) {
        int result = profileDao.createProfile(newMem.getNickName());
        System.out.println("소셜 닉 삽입: "+result);
        return result;
    }


    // 네이버 사용자 정보 반환
    @Override
    public Map<String, Object> getNaverUser(String token) {

        Map<String, Object> naverUser = new HashMap<>();
        String userInfo_URI = "https://openapi.naver.com/v1/nid/me";

        try {
            URL url = new URL(userInfo_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청 header에 담을 내용
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
//            System.out.println("네이버 응답 코드: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String brLine = "";
            String result = "";
            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }
            System.out.println("response: "+result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();

            String email = response.getAsJsonObject().get("email").getAsString();
            String userName = response.getAsJsonObject().get("name").getAsString();

            naverUser.put("userName", userName);
            naverUser.put("email", email);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return naverUser;
    }


    @Override
    public Member pwdChEmail(Member userEmail) {
        return memberDao.pwdChEmail(userEmail);
    }


    @Override
    public Member findUserNo(String email) {
        return memberDao.findUserNo(email);
    }


    @Override
    public int updateToken(Member isSocial) {
        return memberDao.updateToken(isSocial);
    }



    @Override
    public int updateProfile(Profile profile) {
        return profileDao.updateProfile(profile);
    }

//
//    public int updateUserProfile(Photo p){
//        return memberDao.updateUserProfile(p);
//    }

    public MyProfileVo getMyProfile(int userNo) {
        Member m = memberDao.findPublic(userNo);
        Profile p = profileDao.findOne(userNo);
        return new MyProfileVo(m, p);
    }

    /**
     * 유저의 프로필사진을 업데이트한다.
     * 이전프로필은 서버에서 삭제된다.
     * @param newP
     * @return
     */
    @Override
    public int insertUserPhoto(Photo newP) {
        Photo oldP = photoDao.findByUser(newP.getUserNo());
        System.out.println(oldP);
        if (oldP != null) {
            fileUtil.deleteFile(oldP);
            photoDao.deletePhoto(oldP.getPhotoNo());
        }

        int result = photoDao.insertPhoto(newP);
        System.out.println(newP);

        result *= profileDao.updateProfilePhoto(newP);
        return result;
    }

    @Override
    public int updatePassword(ChangePwdVo cpw) {
        return memberDao.updatePassword(cpw);
    }

//    public Member selectMember(int userNo){
//        return memberDao.selectMember(userNo);
//    }


    // 일반 회원 탈퇴
    @Override
    public int deleteUser(int userNo) {
        int result1 = memberDao.deleteUser(userNo);
        int result2 = profileDao.deleteUser(userNo);

        int result = result1 * result2;
        return result;
    }


    // 탈퇴를 위한 Naver User 토큰 조회
    @Override
    public String selectAccessToken(int userNo) {
        String token = memberDao.selectAccessToken(userNo);
        System.out.println("서비스 token: "+token);
        return token;
    }


    // Naver 유저 api 끊기 -> 회원 탈퇴
    public int deleteNaver(Profile profile, String token) {

        int result1 = memberDao.deleteUser(profile.getUserNo()); // 멤버에서 상태 0으로 변경
        int result2 = profileDao.deleteUser(profile.getUserNo()); // 프로필에서 삭제
        int result3 = deleteNaverToken(token); // 네이버 연동 해제

        int result = result1 * result2 * result3;
        return result;

    }


    // 네이버 연동 해제
    public int deleteNaverToken(String token) {
//        System.out.println("탈퇴 서비스 토큰: "+token);

        String clientId = "gby5MZqE_2ShDpfOnFIS";
        String clientSecret = "OyOjXUomy0";

        String reqUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="+clientId+
                "&client_secret="+clientSecret+"&access_token="+token+"&service_provider=NAVER";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
//            System.out.println("탈퇴 응답코드: "+responseCode);

            BufferedReader br;
            if(responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String brLine = "";
            String result = "";
            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }

            br.close();

            if(responseCode == 200) {
                return 1;
            } else {
                return 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    // 카카오 유저 삭제
    @Override
    public int deleteKakao(Profile profile, String token) {

        int result1 = memberDao.deleteUser(profile.getUserNo()); // 멤버에서 상태 0으로 변경
        int result2 = profileDao.deleteUser(profile.getUserNo()); // 프로필에서 삭제
        int result3 = deleteKakaoToken(token); // 카카오 api 연동 해제

        int result = result1 * result2 * result3;
        return result;
    }


    public int deleteKakaoToken(String token) {
//        System.out.println(token);

        String reqURL = "https://kapi.kakao.com/v1/user/unlink";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer "+token);

            int responseCode = conn.getResponseCode();
//            System.out.println("카카오 회원탈퇴 응답코드: "+responseCode);

            BufferedReader br;
            if(responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String brLine = "";
            String result = "";
            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }

            br.close();

            if(responseCode == 200) {
                return 1;
            } else {
                return 0;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Profile selectMyProfile(int userNo) {
        return profileDao.findOne(userNo);
    }

    public Member selectMembers(int userNo){
        return memberDao.selectMembers(userNo);
    }

    public List<Profile> searchUser(String userNick) {
        return profileDao.searchUser(userNick);
    }

    public Profile MyPageNickCheck(String checkNick){
        return profileDao.MyPageNickCheck(checkNick);
    }

    public Profile updateMyNick(Profile pro){
        return profileDao.updateMyNick(pro);
    }

    public Profile updateMyContent(Profile p){
        return profileDao.updateMyContent(p);
    }
}
