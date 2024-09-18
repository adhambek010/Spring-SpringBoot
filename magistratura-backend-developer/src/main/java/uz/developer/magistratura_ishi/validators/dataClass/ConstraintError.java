package uz.developer.magistratura_ishi.validators.dataClass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstraintError {
    private String field;
    private String value;
    private String message;
}
