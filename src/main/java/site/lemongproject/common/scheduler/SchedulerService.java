package site.lemongproject.common.scheduler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import site.lemongproject.common.request.HolidayRequestURLBuilder;
import site.lemongproject.common.response.holidayResponse.DataGoResponseMapper;
import site.lemongproject.common.response.holidayResponse.Holiday;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

import site.lemongproject.common.scheduler.model.dao.HolidayDao;
import site.lemongproject.common.scheduler.model.dto.OfficialHoliday;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {
    final private Gson gson;
    final private HolidayDao holidayDao;

    //    @Scheduled(cron = "",zone = "Asia/Seoul")
    public int updateHoliday() {
        Properties prop = getHolidayProp();
        HolidayRequestURLBuilder request = new HolidayRequestURLBuilder();
        request.setUrl(prop.getProperty("url"));
        request.setServiceKey(prop.getProperty("key"));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        int year = calendar.getWeekYear();
        request.setYear(year);
        List<OfficialHoliday> officialHolidays = new ArrayList<>(40);
//        for (int i = 1; i <=12; i++) {
            request.setMonth(2);
            getRequest(officialHolidays,request.getUri());
//        }
        int result = holidayDao.insertMany(officialHolidays);
        return result;
    }

    private void parseHolidays(List<OfficialHoliday> officialHolidays, List<Holiday> holidays) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");

        try {
            for (Holiday holiday : holidays) {
                if (holiday.getIsHoliday().equals("Y")) {
                    OfficialHoliday oh = new OfficialHoliday();
                    oh.setHoliday(new Date(format.parse(holiday.getLocdate()).getTime()));
                    oh.setHolidayName(holiday.getDateName());
                    officialHolidays.add(oh);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void getRequest(List<OfficialHoliday> officialHolidays,URI uri) {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream resp = client.send(
                    HttpRequest.newBuilder(uri)
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofInputStream()
            ).body();
            Document document = builder.parse(resp);
            NodeList items=document.getElementsByTagName("item");
            for(int i=0;i<items.getLength();i++){
                NodeList holiday=items.item(i).getChildNodes();
                System.out.println(items.item(i).getTextContent());
                String isHoliday=holiday.item(2).getTextContent();
                if(!isHoliday.equals("Y")){
                    continue;
                }
                String holidate="20"+holiday.item(0).getTextContent();
                String holidayName=holiday.item(5).getTextContent();
                OfficialHoliday oholiday=new OfficialHoliday();
               oholiday.setHoliday(Date.valueOf(holidate));
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
        String propPath = SchedulerService.class.getResource("/security/HolidayApi.properties").getPath();
        try {
            prop.load(new FileInputStream(propPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}
