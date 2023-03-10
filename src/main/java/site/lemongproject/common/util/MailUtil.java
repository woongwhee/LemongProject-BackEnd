package site.lemongproject.common.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;
import site.lemongproject.common.domain.dto.MailMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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

    public void htmlSend(MailMessage mail) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setCharset("euc-kr");
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(true);
            email.setSubject(mail.getSubject());
            email.setFrom(fromMail);
            email.setHtmlMsg(mail.getMessage());
            email.addTo(mail.getEmail());
            email.send();
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    // 랜덤 숫자 발생
    public static String ranNum() {

        Random random = new Random();
        int createNum = 0; // 1자리 난수
        String ranNum = ""; // 1자리 난수 형변환 변수
        int letter = 6; // 6자리 난수
        String resultNum = "";

        for(int i=0; i<letter; i++) {
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            resultNum += ranNum;
        }
        return resultNum;
    }


    // 보낼 값 셋팅
    // 보낼 값 셋팅
    public MailMessage setConfirmMail(String email, String ranNum) {
        String subject = "[LEMONG] 인증번호 입니다.";
        String message = "<h1> 본인 인증을 위하여 아래의 인증 번호를 입력해주세요.</h1> \n\n" +"<h2>"+ ranNum+"</h2>";
        MailMessage mail = new MailMessage(email,subject,message);
        return mail;
    }

}
