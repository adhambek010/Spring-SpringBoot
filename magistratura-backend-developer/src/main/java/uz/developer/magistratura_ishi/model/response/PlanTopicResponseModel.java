package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanTopicResponseModel {
    private String identifier;
    private Boolean active;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String studentUsername;
    private String themeId;
    private Integer bobNumber;
    private String bobName;

    private Boolean timeIsNear = false;
    private Boolean unfulfilledAdmin = false;
}
