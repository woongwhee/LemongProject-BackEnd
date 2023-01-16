package site.lemongproject.common.scheduler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import site.lemongproject.common.request.HolidayRequestURLBuilder;
import site.lemongproject.common.response.holidayResponse.DataGoResponseMapper;
import site.lemongproject.common.response.holidayResponse.Holiday;

import java.io.FileInputStream;
import java.io.IOException;
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

//@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {
    final private Gson gson;
    final private HolidayDao holidayDao;

    //    @Scheduled(cron = "",zone = "Asia/Seoul")
    public int updateHoliday(){
        Properties prop=getHolidayProp();
        HolidayRequestURLBuilder request=new HolidayRequestURLBuilder();
        request.setUrl(prop.getProperty("url"));
        request.setServiceKey(prop.getProperty("key"));
        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        int year=calendar.getWeekYear();
        request.setYear(year);
        List<OfficialHoliday> officialHolidays=new ArrayList<>(40);
        for (int i = 1; i < 12; i++) {
            request.setMonth(i);
            List<Holiday> holidays=getRequest(request.getUri());
            parseHolidays(officialHolidays,holidays);
        }
        int result=holidayDao.insertMany(officialHolidays);
        return result;
    }
    private void parseHolidays(List<OfficialHoliday> officialHolidays,List<Holiday> holidays){
        SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
        try {
        for (Holiday holiday: holidays) {
            if(holiday.getIsHoliday().equals("Y")){
                OfficialHoliday oh=new OfficialHoliday();
                oh.setHoliday(new Date(format.parse(holiday.getLocdate()).getTime()));
                oh.setHolidayName(holiday.getDateName());
                officialHolidays.add(oh);
            }
        }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Holiday> getRequest(URI uri) {
        HttpClient client=HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        try {
                String resp = client.send(
                        HttpRequest.newBuilder(uri)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                ).body();
                Type type = new TypeToken<DataGoResponseMapper<Holiday>>(){}.getType(); //객체생성 후 gettype호출 // -> 상속받은애만 객체를 만들 수 있게
                DataGoResponseMapper<Holiday> response = gson.fromJson(resp, type);
                return response.getResponse().getBody().getItems();
            } catch (Exception ex) {
                ex.printStackTrace();
        }
        throw new RuntimeException();
    }
    private Properties getHolidayProp(){
        Properties prop=new Properties();
        String propPath="classpath:security/HolidayApi.properties";
        try {
            prop.load(new FileInputStream(propPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}
