package site.lemongproject.web.feed.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReplyAlarmList {
    private int alrNo;
    private int userNo; // 받은사람
    private int refType;
    private int refNo;
    private int sendUser; // 보낸사람
    private String message;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+9")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime alrAt;

    private int read;
}
