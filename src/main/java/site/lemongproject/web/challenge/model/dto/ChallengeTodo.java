package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChallengeTodo {
    long todoNo;
    int challengeNo;
    int userNo;
    LocalDate todoDate;
    String todoContent;
    int value;
    boolean clear;
}
