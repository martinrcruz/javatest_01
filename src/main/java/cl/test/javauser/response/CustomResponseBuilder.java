package cl.test.javauser.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class CustomResponseBuilder {
    private CustomResponseBuilder() {}

    public static <T> ResponseEntity<CustomResponse<T>> ok(T data) {
        return ResponseEntity.ok(CustomResponse.ok(data));
    }

    public static ResponseEntity<CustomResponse<String>> error(String message) {
        return ResponseEntity.status(500).body(CustomResponse.error(message));
    }

    public static <T> ResponseEntity<CustomResponse<T>> custom(HttpStatus status, int code, String message, T data) {
        return ResponseEntity.status(status).body(new CustomResponse<>(code, message, data));
    }
}
