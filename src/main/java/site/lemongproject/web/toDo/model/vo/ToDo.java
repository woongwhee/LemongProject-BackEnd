package site.lemongproject.web.toDo.model.vo;

import lombok.Data;
import site.lemongproject.web.member.model.vo.Profile;

import java.sql.Date;

@Data
public class ToDo {

    private long todoNo; //투두넘버

    private Date todoDate; //투두리스트 날짜??

    private int userNo; //작성자

    private String todoContent; //작성내용

    private boolean todoClear; //완료여부

    private int todoValue; //순서가중치

}
