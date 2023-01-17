package site.lemongproject.common.response.holidayResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataGoResponseMapper<T> {
    private DataGoResponse<T> response;
}
