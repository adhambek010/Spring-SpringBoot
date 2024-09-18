package uz.developer.magistratura_ishi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginResponseModel {

    private String studentId;
    private String themeId;
}
