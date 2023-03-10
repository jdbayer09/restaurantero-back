package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @Column(name = "is_menu", nullable = false)
    private boolean isMenu = false;
}
