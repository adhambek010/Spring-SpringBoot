package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

@Data
public class StudentEditReceiveModel {
    private String userId;
    private String firstname;
    private String lastname;
    private String midname;
    private String username;
    private String kafedra;
    private String studentGroup;
}
