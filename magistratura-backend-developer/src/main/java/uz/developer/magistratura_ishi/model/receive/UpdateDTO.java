package uz.developer.magistratura_ishi.model.receive;

import lombok.Data;

@Data
public class UpdateDTO {

    private String phoneNumber;
    private String email;
    private ImageDTO imageDTO;

    private String userId;

    public UpdateDTO(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UpdateDTO(String phoneNumber, String email, ImageDTO imageDTO) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageDTO = imageDTO;
    }

    public UpdateDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }
}
