package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "permisos")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permiso extends BaseEntity {
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;
    @Column(name = "descripcion", length = 300, nullable = false)
    private String descripcion;
}
