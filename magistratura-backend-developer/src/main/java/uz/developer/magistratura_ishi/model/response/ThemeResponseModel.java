package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

@Data
public class ThemeResponseModel {
    private String identifier;
    private String name;
    private String academicYear;
    private String direction;
    private String teacherUsername;
    private String studentUsername;
}
