package co.com.jdbsoft.restaurantero.api.service.impl;

import co.com.jdbsoft.restaurantero.api.repository.UsuarioRepository;
import co.com.jdbsoft.restaurantero.api.service.PermisoService;
import co.com.jdbsoft.restaurantero.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public boolean validateLoginWeb(String usuario) {
        return repository
                .fetchAllCargoPermisosByUsuario(usuario)
                .stream()
                .anyMatch(cargoPermiso -> cargoPermiso.getPermiso().getId().equals(PermisoService.PERMISO_ACCESO_WEB_ID));
    }

    @Override
    public boolean validateLoginApp(String usuario) {
        return repository
                .fetchAllCargoPermisosByUsuario(usuario)
                .stream()
                .anyMatch(cargoPermiso -> cargoPermiso.getPermiso().getId().equals(PermisoService.PERMISO_ACCESO_APP_ID));
    }
}
