package cl.test.javauser.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdatedTokenResponse {
    @Schema(description = "UUID del usuario", example = "12345678-1234-1234-1234-123456789012")
    private UUID uuid;

    @Schema(description = "Nombre del usuario", example = "Martín Ríos")
    private String nombre;

    @Schema(description = "Token JWT", example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    private String token;
}
