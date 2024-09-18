package uz.developer.magistratura_ishi.validators.dataClass;

import lombok.Data;

import java.util.List;

@Data
public class ValidatorError {
    private String message;
    private List<ConstraintError> errors;
    private String errorCode;

    public ValidatorError(String message, List<ConstraintError> errors) {
        this.message = message;
        this.errors = errors;
    }
}
