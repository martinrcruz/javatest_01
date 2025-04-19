package cl.test.javauser.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    @NotBlank(message = "El correo electrónico es requerido.")
    @Email(message = "El correo electrónico es inválido.")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "password")
    @NotBlank(message = "La contraseña es requerida.")
    private String contrasena;
}
