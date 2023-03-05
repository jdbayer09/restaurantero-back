package co.com.jdbsoft.restaurantero.api.models.entities.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@ToString
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name="active", nullable = false)
    private boolean activo = true;
    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaActualizacion;
    @PreUpdate
    private void preUpdate() {
        this.fechaActualizacion = new Date(System.currentTimeMillis());
    }
    @PrePersist
    private void prePersist() {
        this.fechaCreacion = new Date(System.currentTimeMillis());
    }

}
