package uz.developer.magistratura_ishi.model.receive;


import lombok.Data;

@Data
public class CalendarPlanReceiveModel {

    private String name;

    private String period;

    private String description;

    private String plan;

    private String studentUsername;

    private String teacherUsername;

}
