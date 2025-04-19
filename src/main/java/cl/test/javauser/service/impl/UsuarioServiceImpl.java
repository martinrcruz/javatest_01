package cl.test.javauser.service.impl;

import cl.test.javauser.dto.UsuarioDTO;
import cl.test.javauser.dto.request.UpdateRequest;
import cl.test.javauser.dto.response.UsuarioResponse;
import cl.test.javauser.entity.Usuario;
import cl.test.javauser.exception.MissingUserDataException;
import cl.test.javauser.exception.UsuarioNotFoundException;
import cl.test.javauser.mapper.TelefonoMapper;
import cl.test.javauser.mapper.UsuarioMapper;
import cl.test.javauser.repository.UsuarioRepository;
import cl.test.javauser.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO getByUuid(UUID uuid) {
        return usuarioRepository.findByUuid(uuid)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el uuid: " + uuid));
    }

    @Override
    public UsuarioDTO getByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el correo: " + correo));
    }

    @Override
    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioMapper::toDTO);
    }

    @Override
    public Page<UsuarioDTO> getAllActivos(Pageable pageable) {
        return usuarioRepository.findAllByActivoTrue(pageable).map(UsuarioMapper::toDTO);
    }

    @Override
    public UsuarioResponse update(UUID uuid, UpdateRequest request) {
        Usuario usuario = usuarioRepository.findByUuid(uuid).orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el uuid: " + uuid));

        if (request.getNombre().isBlank() || request.getCorreo().isBlank()) {
            throw new MissingUserDataException("nombre o correo");
        }

        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefonos(TelefonoMapper.toEntityList(request.getTelefonos()));
        Usuario saved = usuarioRepository.save(usuario);
        return UsuarioResponse.builder()
                .id(saved.getId())
                .nombre(saved.getNombre())
                .correo(saved.getCorreo())
                .telefonos(TelefonoMapper.toDTOList(saved.getTelefonos()))
                .build();
    }

    @Override
    public String delete(UUID uuid) {
        Usuario usuario = usuarioRepository.findByUuid(uuid).orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el uuid: " + uuid));
        usuarioRepository.delete(usuario);
        return "Se elimino el usuario con el uuid: " + uuid;
    }

}
