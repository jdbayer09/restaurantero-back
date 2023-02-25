package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Usuario extends BaseEntity {

    @Column(name = "usuario", nullable = false, length = 100, unique = true)
    String usuario;
    @Column(name = "contrasena", nullable = false, length = 300)
    String contrasena;
    @Column(name = "nombres", nullable = false, length = 150)
    String nombres;
    @Column(name = "apellidos", nullable = false, length = 150)
    String apellidos;
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    Cargo cargo;

}
