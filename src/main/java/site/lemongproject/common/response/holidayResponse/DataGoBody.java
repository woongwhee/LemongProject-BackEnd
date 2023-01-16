package site.lemongproject.common.response.holidayResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DataGoBody <T>{
    private List<T> items;
    private int numberOfRows;
    private int pageNo;
    private int totalCount;
}
