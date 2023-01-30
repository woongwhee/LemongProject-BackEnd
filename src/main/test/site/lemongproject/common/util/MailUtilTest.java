package site.lemongproject.common.util;

import org.junit.Test;
import site.lemongproject.common.domain.dto.MailMessage;

import java.util.*;

import static org.junit.Assert.*;

public class MailUtilTest {
    @Test
    public void mail(){
        MailUtil mu=new MailUtil();
        MailMessage mm=new MailMessage("제목입니다.","제목입니다.","dndgnlwls@gmail.com");
        mu.send(mm);
    }
}