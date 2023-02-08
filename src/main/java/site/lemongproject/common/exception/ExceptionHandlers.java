package site.lemongproject.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.lemongproject.common.response.ResponseBody;
import site.lemongproject.common.response.ResponseBuilder;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(IsNotWriterException.class)
    public ResponseBody handlerIsNotWriter(){
        return ResponseBuilder.authError();
    }


}
