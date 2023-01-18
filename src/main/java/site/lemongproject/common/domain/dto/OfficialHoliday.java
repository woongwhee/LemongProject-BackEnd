package site.lemongproject.common.domain.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class OfficialHoliday {
    private int holidayNo;
    private Date holiday;
    private String holidayName;
    private String isHoliday;
}
