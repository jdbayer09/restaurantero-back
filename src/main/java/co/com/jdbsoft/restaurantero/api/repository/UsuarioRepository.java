package co.com.jdbsoft.restaurantero.api.repository;

import co.com.jdbsoft.restaurantero.api.models.entities.CargoPermiso;
import co.com.jdbsoft.restaurantero.api.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String usuario);

    @Query("SELECT c.permisos FROM Usuario u LEFT JOIN FETCH Cargo c ON c.id = u.cargoUsuario.id WHERE u.login = :usuario")
    List<CargoPermiso> fetchAllCargoPermisosByUsuario(String usuario);
}
