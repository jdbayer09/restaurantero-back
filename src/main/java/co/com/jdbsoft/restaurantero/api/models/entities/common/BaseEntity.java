package co.com.jdbsoft.restaurantero.api.models.entities.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    Long id;
    @Column(name="active", nullable = false)
    boolean activo = true;
    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "timestamp")
    ZonedDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "timestamp")
    ZonedDateTime fechaActualizacion;
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = ZonedDateTime.now();
    }
    @PrePersist
    private void prePersist() {
        this.fechaCreacion = ZonedDateTime.now();
    }

}
