package co.com.jdbsoft.restaurantero.api.models.entities.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public abstract class MasterBaseEntity extends BaseEntity {

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    String nombre;
    @Column(name = "descripcion", length = 300, nullable = false)
    String descripcion;

}
