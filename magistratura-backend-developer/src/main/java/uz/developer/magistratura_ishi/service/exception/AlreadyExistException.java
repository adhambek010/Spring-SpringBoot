package uz.developer.magistratura_ishi.service.exception;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message) {
        super(message);
    }
}
