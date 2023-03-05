package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cargos")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4046614948556884249L;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cargo")
    @JsonManagedReference
    @ToString.Exclude
    private List<CargoPermiso> permisos;
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;
    @Column(name = "descripcion", length = 300, nullable = false)
    private String descripcion;
    @Column(name = "visible_al_editar", nullable = false)
    private boolean visibleAlEditar = true;
    @Column(name = "visible_al_listar", nullable = false)
    private boolean visibleAlListar = true;
}