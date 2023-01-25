package site.lemongproject.common.response;

public class ResponseBuilder{


    public static ResponseBody success(Object o){
        ResponseBody response=new ResponseBody();
        response.setCode("2000");
        response.setMessage("SUCCESS");
        response.setResult(o);
        return response;
    }

    public static ResponseBody unLogin(Object o){
        ResponseBody response=new ResponseBody();
        response.setCode("4000");
        response.setMessage("UNLOGIN");
        response.setResult(o);
        return response;
    }

    public static ResponseBody unJoin(int num) {
        ResponseBody response = new ResponseBody();
        response.setCode("400");
        response.setMessage("UNJOIN");
        response.setResult(num);
        return response;
    }


}
