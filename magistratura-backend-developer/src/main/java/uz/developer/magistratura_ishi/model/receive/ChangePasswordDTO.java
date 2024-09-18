package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String studentUsername;

    public ChangePasswordDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
