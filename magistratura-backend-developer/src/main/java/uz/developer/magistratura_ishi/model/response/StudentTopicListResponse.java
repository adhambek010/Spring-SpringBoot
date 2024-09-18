package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class StudentTopicListResponse {

    private String planTopicId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
