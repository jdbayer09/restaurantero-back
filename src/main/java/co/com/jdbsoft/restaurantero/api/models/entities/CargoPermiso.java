package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(
        name = "cargos_permisos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cargo_id", "permiso_id"})
)
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class CargoPermiso extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    @JsonBackReference
    Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    Permiso permiso;
}
