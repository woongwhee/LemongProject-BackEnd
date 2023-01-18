package site.lemongproject.common.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;
import site.lemongproject.common.domain.dto.MailMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
public class MailUtil {
    private Properties prop;
    private String userName;
    private String password;
    private String host;
    private int port;
    private String fromMail;
    public MailUtil(){
        prop=new Properties();
        String filePath=MailUtil.class.getResource("/security/mail.properties").getPath();
        try {
            prop.load(new FileInputStream(filePath));
            userName=prop.getProperty("username");
            password=prop.getProperty("password");
            host=prop.getProperty("host");
            port= Integer.parseInt(prop.getProperty("port"));
            fromMail=prop.getProperty("email");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(MailMessage mail){
        //google메일 정책 변경으로 어려워짐
        try {
        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setSSLOnConnect(true);
        email.setSubject(mail.getSubject());
        email.setFrom(fromMail);
        email.setMsg(mail.getMessage());
        email.addTo(mail.getEmail());
        email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }


}
