package uz.developer.magistratura_ishi.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;

@Data
public class ThemeReceiveModel {
    private String name;
    private String academicYear;
    private String direction;

    private String teacherUsername;
    private String studentUsername;

    private String identifier;

    public ThemeReceiveModel(String name, String academicYear, String direction, String teacherUsername, String studentUsername) {
        this.name = name;
        this.academicYear = academicYear;
        this.direction = direction;
        this.teacherUsername = teacherUsername;
        this.studentUsername = studentUsername;
    }
}
