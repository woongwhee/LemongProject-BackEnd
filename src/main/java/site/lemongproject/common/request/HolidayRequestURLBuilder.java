package site.lemongproject.common.request;

import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Setter
public class HolidayRequestURLBuilder {
    private String url;
    private String serviceKey;
    private int year;
    private int month;
    public HolidayRequestURLBuilder() {
    }
    public URI getUri(){
        StringBuffer urlBuilder=new StringBuffer(url);
        try {
        urlBuilder.append("?serviceKey="+ serviceKey);
        urlBuilder.append("&solYear="+year);
        String zeroMonth=month<10?"0"+month:month+"";
        urlBuilder.append("&solMonth="+zeroMonth);
            System.out.println(urlBuilder);
            return new URI(urlBuilder.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
        }
    }
}
