package site.lemongproject.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailMessage {
    private String email;
    private String subject;
    private String message;
}
