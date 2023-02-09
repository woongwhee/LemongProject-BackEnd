package site.lemongproject.web.member.model.dao;

import site.lemongproject.web.member.model.vo.EmailConfirm;

public interface EmailConfirmDao {

    int isExEmail(String email);
    int checkConfirm(EmailConfirm confirm);
    int insertConfirm(EmailConfirm confirm);
    int deleteAnother(EmailConfirm confirm);
}
