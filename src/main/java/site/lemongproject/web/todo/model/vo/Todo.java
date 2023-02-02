package site.lemongproject.web.todo.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
//시/분/초 값을 가져오려면 java.util.Date로

@Data
public class Todo {

    private long todoNo; //투두넘버

    @JsonFormat(pattern = "yyMMdd" ,timezone = "GMT+9") //JSON의 165030033같은 이상한 날짜값을 제대로 변환시켜준다.
    //private Date todoDate2;

    private String todoDate; //투두리스트 날짜

    private int userNo; //작성자

    private String todoContent; //작성내용

    private boolean clear; //완료여부

    private int value; //순서가중치

}
