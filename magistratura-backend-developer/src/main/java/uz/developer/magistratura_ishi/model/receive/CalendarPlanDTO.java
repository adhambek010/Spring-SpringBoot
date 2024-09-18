package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

@Data
public class CalendarPlanDTO {
    private String calendarPlanId;
    private String name;
    private String period;
    private String description;
    private String plan;
}
