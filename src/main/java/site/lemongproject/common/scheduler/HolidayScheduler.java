package site.lemongproject.common.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import site.lemongproject.common.request.HolidayRequestURLBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import site.lemongproject.common.domain.dao.HolidayDao;
import site.lemongproject.common.domain.dto.OfficialHoliday;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
@Slf4j
@Component
@RequiredArgsConstructor
public class HolidayScheduler {
    final private HolidayDao holidayDao;
//    매년 1월1일 한국천문연구원 특일 정보 api를 이용해 다음해의 공유일을 얻어온다.
    @Scheduled(cron = "0 0 1 1 1 ?",zone = "Asia/Seoul")
    @Transactional
    public int updateHoliday() {
        Properties prop = getHolidayProp();
        HolidayRequestURLBuilder request = new HolidayRequestURLBuilder();
        request.setUrl(prop.getProperty("url"));
        request.setServiceKey(prop.getProperty("key"));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        int year = calendar.getWeekYear();
        List<OfficialHoliday> officialHolidays = new ArrayList<>(60);
        request.setYear(year+1);
        for (int i = 1; i <= 12; i++) {
                request.setMonth(i);
                getRequest(officialHolidays, request.getUri());
        }
        int result = holidayDao.insertMany(officialHolidays);
        return result;
    }
    private void getRequest(List<OfficialHoliday> officialHolidays,URI uri) {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream resp = client.send(
                    HttpRequest.newBuilder(uri).GET().build(),
                    HttpResponse.BodyHandlers.ofInputStream()
            ).body();
            Document document = builder.parse(resp);
            System.out.println(resp);
            NodeList items=document.getElementsByTagName("item");
            for(int i=0;i<items.getLength();i++){
                NodeList holiday=items.item(i).getChildNodes();
                String isHoliday=holiday.item(2).getTextContent();
                if(!isHoliday.equals("Y")){
                    continue;
                }
                String holidate=holiday.item(3).getTextContent();
                String holidayName=holiday.item(1).getTextContent();
                OfficialHoliday oholiday=new OfficialHoliday();
                oholiday.setHoliday(LocalDate.parse(holidate));
                oholiday.setHolidayName(holidayName);
                officialHolidays.add(oholiday);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
    private Properties getHolidayProp() {
        Properties prop = new Properties();
        String propPath = HolidayScheduler.class.getResource("/security/HolidayApi.properties").getPath();
        try {
            prop.load(new FileInputStream(propPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}
