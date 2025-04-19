package cl.test.javauser.exception;

import cl.test.javauser.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<CustomResponse<String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomResponse<>(409, ex.getMessage(), null));
    }

    @ExceptionHandler(MissingUserDataException.class)
    public ResponseEntity<CustomResponse<String>> handleMissingUserDataException(MissingUserDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse<>(400, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponse<>(500, ex.getMessage(), null));
    }
}
