package co.com.jdbsoft.restaurantero.api.models.entities;

import co.com.jdbsoft.restaurantero.api.models.entities.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Usuario extends BaseEntity implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = -8051751340197848209L;
    @Column(name = "login", nullable = false, length = 100, unique = true)
    private String login;
    @Column(name = "contrasena", nullable = false, length = 300)
    private String contrasena;
    @Column(name = "nombres", nullable = false, length = 150)
    private String nombres;
    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargoUsuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.cargoUsuario.getNombre()));
    }

    @Override
    public String getPassword() {
        return this.getContrasena();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isEnabled() {
        return this.isActivo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}