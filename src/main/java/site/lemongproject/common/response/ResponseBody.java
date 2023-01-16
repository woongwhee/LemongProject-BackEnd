package site.lemongproject.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * 일반적인 responsebody형식
 */
public class ResponseBody <T>{
    private String code;
    private String message;
    private T result;
}
