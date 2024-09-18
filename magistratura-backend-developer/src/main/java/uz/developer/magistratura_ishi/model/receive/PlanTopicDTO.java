package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanTopicDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String bobName;

    private String planTopicId;
    private String username;
    private String themeId;

    public PlanTopicDTO(LocalDate startDate, LocalDate endDate, String name, String planTopicId, String username, String bobName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.planTopicId = planTopicId;
        this.username = username;
        this.bobName = bobName;
    }
}
