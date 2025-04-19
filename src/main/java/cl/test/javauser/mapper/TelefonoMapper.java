package cl.test.javauser.mapper;

import cl.test.javauser.dto.TelefonoDTO;
import cl.test.javauser.entity.Telefono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TelefonoMapper {
    public static Telefono toEntity(TelefonoDTO dto) {
        Telefono telefono = new Telefono();
        telefono.setNumero(dto.getNumero());
        telefono.setCodigoCiudad(dto.getCodigoCiudad());
        telefono.setCodigoPais(dto.getCodigoPais());
        return telefono;
    }
    public static Set<Telefono> toEntityList(Set<TelefonoDTO> dtos) {
        return dtos.stream().map(TelefonoMapper::toEntity).collect(Collectors.toSet());
    }

    public static TelefonoDTO toDTO(Telefono entity) {
        TelefonoDTO dto = new TelefonoDTO();
        dto.setNumero(entity.getNumero());
        dto.setCodigoCiudad(entity.getCodigoCiudad());
        dto.setCodigoPais(entity.getCodigoPais());
        return dto;
    }

    public static Set<TelefonoDTO> toDTOList(Set<Telefono> entities) {
        return entities.stream().map(TelefonoMapper::toDTO).collect(Collectors.toSet());
    }
}
