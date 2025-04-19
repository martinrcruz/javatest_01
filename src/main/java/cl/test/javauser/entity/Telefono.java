package cl.test.javauser.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "javauser_telefono")
@Data
public class Telefono {
    @Id
    @GeneratedValue
    private Long id;

    private String numero;

    @Column(name = "codigo_ciudad")
    private String codigoCiudad;

    @Column(name = "codigo_pais")
    private String codigoPais;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean activo;
}
