package cl.test.javauser.service;

import cl.test.javauser.dto.UsuarioDTO;
import cl.test.javauser.dto.request.UpdateRequest;
import cl.test.javauser.dto.response.UsuarioResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUsuarioService {
    UsuarioDTO getByUuid(UUID uuid);
    UsuarioDTO getByCorreo(String correo);
    Page<UsuarioDTO> getAll(Pageable pageable);
    Page<UsuarioDTO> getAllActivos(Pageable pageable);
    UsuarioResponse update(UUID uuid, UpdateRequest request);
    String delete(UUID uuid);

}
