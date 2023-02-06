package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChallengeTodo {
    private long todoNo;
    private int challengeNo;
    private int userNo;
    private LocalDate todoDate;
    private String todoContent;
    private int value;
    private boolean clear;
}
