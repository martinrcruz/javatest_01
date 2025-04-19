package cl.test.javauser.controller;

import cl.test.javauser.dto.UsuarioDTO;
import cl.test.javauser.dto.request.UpdateRequest;
import cl.test.javauser.dto.response.UsuarioResponse;
import cl.test.javauser.response.CustomResponse;
import cl.test.javauser.response.CustomResponseBuilder;
import cl.test.javauser.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @Operation(summary = "Obtener Usuario por UUID", description = "Obtiene un usuario por su UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<CustomResponse<UsuarioDTO>> getByUuid(@PathVariable UUID uuid) {
        return CustomResponseBuilder.ok(usuarioService.getByUuid(uuid));
    }

    @Operation(summary = "Obtener Usuario por Correo", description = "Obtiene un usuario por su Correo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/correo/{correo}")
    public ResponseEntity<CustomResponse<UsuarioDTO>> getByCorreo(@PathVariable String correo) {
        return CustomResponseBuilder.ok(usuarioService.getByCorreo(correo));
    }

    @Operation(summary = "Obtener Usuarios", description = "Obtiene una lista de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("")
    public ResponseEntity<CustomResponse<Page<UsuarioDTO>>> getAll(Pageable pageable) {
        return CustomResponseBuilder.ok(usuarioService.getAll(pageable));
    }

    @Operation(summary = "Obtener Usuarios Activos", description = "Obtiene una lista de usuarios activos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/activos")
    public ResponseEntity<CustomResponse<Page<UsuarioDTO>>> getAllActivos(Pageable pageable) {
        return CustomResponseBuilder.ok(usuarioService.getAllActivos(pageable));
    }

    @Operation(summary = "Actualizar Usuario", description = "Actualiza un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<CustomResponse<UsuarioResponse>> update(@PathVariable UUID uuid, @RequestBody UpdateRequest request) {
        return CustomResponseBuilder.ok(usuarioService.update(uuid, request));
    }

    @Operation(summary = "Eliminar Usuario", description = "Elimina un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<CustomResponse<String>> delete(@PathVariable UUID uuid) {
        return CustomResponseBuilder.ok(usuarioService.delete(uuid));
    }
}
