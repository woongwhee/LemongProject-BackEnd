package site.lemongproject.web.todo.model.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OfficialHoliday {
    private int holidayNo;
    private LocalDate holiday;
    private String holidayName;
}
