package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

@Data
public class CalendarPlanResponseModel {
    private String identifier;
    private String name;

    private String period;

    private String description;

    private String plan;

    private String studentUsername;

    private String teacherUsername;
}
