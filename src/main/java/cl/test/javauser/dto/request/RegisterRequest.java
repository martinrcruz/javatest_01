package cl.test.javauser.dto.request;

import cl.test.javauser.annotation.ValidMail;
import cl.test.javauser.annotation.ValidPassword;
import cl.test.javauser.dto.TelefonoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
public class RegisterRequest {

    @Schema(description = "Nombre del usuario", example = "Martín Ríos")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    @ValidMail
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "password")
    @ValidPassword
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;

    @Schema(description = "Teléfonos del usuario", example = "[{\"numero\": \"123456789\"}]")
    private Set<TelefonoDTO> telefonos;
}
