package cl.test.javauser.mapper;

import cl.test.javauser.dto.UsuarioDTO;
import cl.test.javauser.dto.request.RegisterRequest;
import cl.test.javauser.entity.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefonos(TelefonoMapper.toEntityList(dto.getTelefonos()));
        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(entity.getNombre());
        dto.setCorreo(entity.getCorreo());
        dto.setTelefonos(TelefonoMapper.toDTOList(entity.getTelefonos()));
        return dto;
    }

    public static Usuario fromRegisterToEntity(RegisterRequest register) {
        Usuario usuario = new Usuario();
        usuario.setNombre(register.getNombre());
        usuario.setTelefonos(TelefonoMapper.toEntityList(register.getTelefonos()));
        usuario.setCorreo(register.getCorreo());
        return usuario;
    }


}
