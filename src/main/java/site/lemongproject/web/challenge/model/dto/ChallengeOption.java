package site.lemongproject.web.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import site.lemongproject.common.exception.OptionLengthException;

@Getter
@Setter
public class ChallengeOption {
    boolean monday;
    boolean tuesday;
    boolean wednesday;
    boolean thursday;
    boolean friday;
    boolean saturday;
    boolean sunday;
    boolean officialHoliday;

    private ChallengeOption() {
    }
    public static ChallengeOption getInstance(String option){
        if(option.length()!=8)throw new OptionLengthException();
        ChallengeOption co=new ChallengeOption();
        co.monday=option.charAt(0)=='1'?true:false;
        co.tuesday=option.charAt(1)=='1'?true:false;
        co.wednesday=option.charAt(2)=='1'?true:false;
        co.thursday=option.charAt(3)=='1'?true:false;
        co.friday=option.charAt(4)=='1'?true:false;
        co.saturday=option.charAt(5)=='1'?true:false;
        co.sunday=option.charAt(6)=='1'?true:false;
        co.officialHoliday=option.charAt(7)=='1'?true:false;
        return co;
    }
    public static String parseOption(ChallengeOption co){
        StringBuffer sb=new StringBuffer();
        sb.append(co.monday?'1':'0');
        sb.append(co.tuesday?'1':'0');
        sb.append(co.wednesday?'1':'0');
        sb.append(co.thursday?'1':'0');
        sb.append(co.friday?'1':'0');
        sb.append(co.saturday?'1':'0');
        sb.append(co.sunday?'1':'0');
        sb.append(co.officialHoliday?'1':'0');
        return sb.toString();
    }




}