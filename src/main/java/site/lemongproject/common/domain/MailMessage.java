package site.lemongproject.common.domain;

import lombok.Data;

@Data
public class MailMessage {
    private String email;
    private String subject;
    private String message;
}
