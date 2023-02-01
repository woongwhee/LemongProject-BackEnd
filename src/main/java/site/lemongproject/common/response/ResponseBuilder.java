package site.lemongproject.common.response;

import site.lemongproject.web.template.model.dto.Template;

import java.util.List;

public class ResponseBuilder{

    public static ResponseBody success(Object o){
        ResponseBody response=new ResponseBody();
        response.setCode("2000");
        response.setMessage("SUCCESS");
        response.setResult(o);
        return response;
    }

    // 로그인 실패
    public static ResponseBody unLogin(Object o){
        ResponseBody response=new ResponseBody();
        response.setCode("4000");
        response.setMessage("UN_LOGIN");
        response.setResult(o);
        return response;
    }

    // 회원가입 실패
    public static ResponseBody unJoin(int num) {
        ResponseBody response = new ResponseBody();
        response.setCode("4001");
        response.setMessage("UN_JOIN");
        response.setResult(num);
        return response;
    }

    // 닉네임 중복
    public static ResponseBody hasSameNick(int num) {
        ResponseBody response = new ResponseBody();
        response.setCode("4003");
        response.setMessage("HAS_SAME_NIK");
        response.setResult(num);
        return response;
    }


    // 이메일 인증번호 전송
    public static ResponseBody failEmail(int num) {
        ResponseBody response = new ResponseBody();
        response.setCode("4005");
        response.setMessage("FAILEMAIL");
        response.setResult(num);
        return response;
    }

    //업로드실패실패
    public static ResponseBody upLoadFail() {
        ResponseBody response = new ResponseBody();
        response.setCode("4006");
        response.setMessage("UPLOAD_FAIL");
        response.setResult(null);
        return response;
    }
    //삭제실패
    public static ResponseBody deleteFail() {
        ResponseBody response = new ResponseBody();
        response.setCode("4007");
        response.setMessage("DELETE_FAIL");
        response.setResult(null);
        return response;
    }
    public static ResponseBody timeOut() {
        ResponseBody response = new ResponseBody();
        response.setCode("4000");
        response.setMessage("TIME_OUT");
        response.setResult(null);
        return response;
    }
    //조회된결과없음
    public static ResponseBody findNothing() {
        ResponseBody response = new ResponseBody();
        response.setCode("4008");
        response.setMessage("FOUND_NOTHING");
        response.setResult(null);
        return response;
    }


    // 인증번호 체크
    public static ResponseBody failAuthEmail(int num) {
        ResponseBody response = new ResponseBody();
        response.setCode("3005");
        response.setMessage("FAILAUTHEMAIL");
        response.setResult(num);
        return response;
    }
    public static ResponseBody serverError() {
        ResponseBody response = new ResponseBody();
        response.setCode("5000");
        response.setMessage("SERVER_ERROR");
        return response;
    }
}
