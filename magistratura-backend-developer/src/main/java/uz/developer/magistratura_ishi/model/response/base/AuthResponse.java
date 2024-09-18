package uz.developer.magistratura_ishi.model.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String userId;
    private String roles;
}
