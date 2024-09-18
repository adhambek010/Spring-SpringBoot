package uz.developer.magistratura_ishi.model.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class ApiResponse {

    private String message;

    private boolean success;

    @JsonProperty("status_code")
    private int statusCode;

    private Object object;

    private String token;

    private String roles;

    public ApiResponse(String message, boolean success, int statusCode) {
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }
}
