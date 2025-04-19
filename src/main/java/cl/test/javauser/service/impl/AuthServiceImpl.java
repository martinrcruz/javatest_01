package cl.test.javauser.service.impl;

import cl.test.javauser.dto.request.LoginRequest;
import cl.test.javauser.dto.request.RegisterRequest;
import cl.test.javauser.dto.response.LoginResponse;
import cl.test.javauser.dto.response.UpdatedTokenResponse;
import cl.test.javauser.dto.response.UsuarioResponse;
import cl.test.javauser.entity.Usuario;
import cl.test.javauser.exception.EmailAlreadyExistsException;
import cl.test.javauser.exception.MissingUserDataException;
import cl.test.javauser.exception.UsuarioNotFoundException;
import cl.test.javauser.mapper.TelefonoMapper;
import cl.test.javauser.mapper.UsuarioMapper;
import cl.test.javauser.repository.UsuarioRepository;
import cl.test.javauser.security.JwtHelper;
import cl.test.javauser.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    @Override
    public LoginResponse login(LoginRequest login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getCorreo(), login.getContrasena()));

        Usuario usuario = usuarioRepository.findByCorreo(login.getCorreo()).orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el correo: " + login.getCorreo()));

        String token = jwtHelper.generarToken(login.getCorreo());
        usuario.setToken(token);
        usuario.setUpdatedAt(LocalDateTime.now());
        usuarioRepository.saveAndFlush(usuario);

        return LoginResponse.builder()
                .usuario(UsuarioMapper.toDTO(usuario))
                .token(token).build();
    }

    @Override
    public UsuarioResponse register(RegisterRequest request) {
        if (request.getNombre().isBlank()) {
            throw new MissingUserDataException("nombre");
        }

        if (request.getCorreo().isBlank()) {
            throw new MissingUserDataException("correo");
        }

        if (request.getContrasena().isBlank()) {
            throw new MissingUserDataException("contraseña");
        }

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new EmailAlreadyExistsException(request.getCorreo());
        }


        Usuario usuario = UsuarioMapper.fromRegisterToEntity(request);
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));
        usuario.setRol(Usuario.Rol.USER);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());
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
    public UpdatedTokenResponse updateToken(UUID uuid) {
        Usuario usuario = usuarioRepository.findByUuid(uuid).orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el uuid: " + uuid));

        String nuevoToken = jwtHelper.generarToken(usuario.getCorreo());
        usuario.setToken(nuevoToken);
        usuario.setUpdatedAt(LocalDateTime.now());
        usuarioRepository.save(usuario);

        return UpdatedTokenResponse.builder()
                .token(nuevoToken)
                .nombre(usuario.getNombre())
                .uuid(uuid)
                .build();
    }
}
