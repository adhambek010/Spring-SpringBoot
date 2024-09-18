package uz.developer.magistratura_ishi.validators.exception;

import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

public class DoUserValidationException extends RuntimeException {

    private static final long serialVersionUID = -8404012653259415363L;

    private ValidatorError validatorError;

    public DoUserValidationException(ValidatorError validatorError) {
        super("User data validation error!");
        this.validatorError = validatorError;
    }

    public ValidatorError getValidatorError() {
        return validatorError;
    }

    public void setValidatorError(ValidatorError validatorError) {
        this.validatorError = validatorError;
    }
}
