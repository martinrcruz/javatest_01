package cl.test.javauser.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class UsuarioDTO {
    private UUID id;
    private String correo;
    private String nombre;
    private Set<TelefonoDTO> telefonos;
    private LocalDateTime creado;
    private LocalDateTime ultimoLogin;
    private String token;
    private boolean activo;
}
