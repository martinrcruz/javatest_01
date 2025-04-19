package cl.test.javauser.service;

import cl.test.javauser.dto.request.LoginRequest;
import cl.test.javauser.dto.request.RegisterRequest;
import cl.test.javauser.dto.response.LoginResponse;
import cl.test.javauser.dto.response.UpdatedTokenResponse;
import cl.test.javauser.dto.response.UsuarioResponse;

import java.util.UUID;

public interface IAuthService {
    LoginResponse login(LoginRequest login);
    UsuarioResponse register(RegisterRequest request);
    UpdatedTokenResponse updateToken(UUID uuid);
}
