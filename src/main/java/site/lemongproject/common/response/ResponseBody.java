package site.lemongproject.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseBody<T>{
    private String code;
    private String message;
    private T result;
    public ResponseBody(){}
    public ResponseBody(T result){
        this.result=result;
    }
}
