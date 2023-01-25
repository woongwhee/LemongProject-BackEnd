package site.lemongproject.web.todo.model.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class Todo {

    private long todoNo; //투두넘버

    private Date todoDate; //투두리스트 날짜

    private int userNo; //작성자

    private String todoContent; //작성내용

    private boolean clear; //완료여부

    private int value; //순서가중치

}
