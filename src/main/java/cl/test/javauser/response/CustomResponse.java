package cl.test.javauser.response;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public CustomResponse() {}

    public CustomResponse(Integer code, String message, T data) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public static <T> CustomResponse<T> ok(T data) {
        return new CustomResponse<>(200, "success", data);
    }

    public static  CustomResponse<String> error(String message) {
        return new CustomResponse<>(500, message, null);
    }
}
