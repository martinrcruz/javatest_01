package cl.test.javauser.repository;

import cl.test.javauser.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.id = :uuid")
    Optional<Usuario> findByUuid(UUID uuid);

    Page<Usuario> findAllByActivoTrue(Pageable pageable);
}
