package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

@Data
public class UserResponseModel {
    private String userId;
    private String firstname;
    private String lastname;
    private String midname;
    private String username;
    private String kafedra;
    private String password;
    private String studentGroup;
    private String email;
    private String phoneNumber;
    private String photoUrl;

}
