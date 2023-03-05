package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "permisos")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permiso extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4224155385729684146L;
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
