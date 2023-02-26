package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "cargos_permisos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cargo_id", "permiso_id"})
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoPermiso extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    @JsonBackReference
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    private Permiso permiso;
}
