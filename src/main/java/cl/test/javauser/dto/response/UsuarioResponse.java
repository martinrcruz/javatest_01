package cl.test.javauser.dto.response;

import cl.test.javauser.dto.TelefonoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UsuarioResponse {
    @Schema(description = "Identificador único del usuario", example = "123e4567-e89b-12d3-a456-426655440000")
    private UUID id;

    @Schema(description = "Nombre del usuario", example = "Martín Ríos")
    private String nombre;

    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    private String correo;

    @Schema(description = "Teléfonos del usuario", example = "[{\"numero\": \"123456789\"}]")
    private Set<TelefonoDTO> telefonos;
}
