package uz.developer.magistratura_ishi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.developer.magistratura_ishi.file.message.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ResponseMessage> llreadyExistException(AlreadyExistException exc) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(exc.getMessage()));
    }

    @ExceptionHandler(DateExpiredException.class)
    public ResponseEntity<ResponseMessage> dateExpiredException(DateExpiredException exc) {
        return ResponseEntity.status(HttpStatus.LOCKED).body(new ResponseMessage("Mavzuni bajarish muddati tugagan! Iltimos " +
                "vazifalarni o'z vaqtida bajaring!"));
    }

    @ExceptionHandler(NoConfirmException.class)
    public ResponseEntity<ResponseMessage> dateExpiredException(NoConfirmException exc) {
        return ResponseEntity.status(HttpStatus.LOCKED).body(new ResponseMessage("Fayl yuklab bo'lmaydi. Bu mavzu o'qtuvchi tomonidan tasdiqlanmagan!"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> dateExpiredException(NotFoundException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(exc.getMessage()));
    }
}