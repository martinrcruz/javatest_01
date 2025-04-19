package cl.test.javauser.controller;

import cl.test.javauser.dto.request.LoginRequest;
import cl.test.javauser.dto.request.RegisterRequest;
import cl.test.javauser.dto.response.LoginResponse;
import cl.test.javauser.dto.response.UsuarioResponse;
import cl.test.javauser.dto.response.UpdatedTokenResponse;
import cl.test.javauser.response.CustomResponse;
import cl.test.javauser.response.CustomResponseBuilder;
import cl.test.javauser.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Autenticación")
public class AuthController {
    private final IAuthService authService;

    @Operation(summary = "Login", description = "Loguea con correo y contraseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/login")
    public ResponseEntity<CustomResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return CustomResponseBuilder.ok(authService.login(request));
    }

    @Operation(summary = "Register", description = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UsuarioResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return CustomResponseBuilder.ok(authService.register(request));
    }

    @Operation(summary = "Update token", description = "Actualiza el token de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PatchMapping("/token/{uuid}")
    public ResponseEntity<CustomResponse<UpdatedTokenResponse>> updateToken(@PathVariable UUID uuid) {
        return CustomResponseBuilder.ok(authService.updateToken(uuid));
    }
}
