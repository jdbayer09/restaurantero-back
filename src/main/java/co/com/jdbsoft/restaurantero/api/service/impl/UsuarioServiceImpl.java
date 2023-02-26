package co.com.jdbsoft.restaurantero.api.service.impl;

import co.com.jdbsoft.restaurantero.api.models.entities.CargoPermiso;
import co.com.jdbsoft.restaurantero.api.repository.UsuarioRepository;
import co.com.jdbsoft.restaurantero.api.service.PermisoService;
import co.com.jdbsoft.restaurantero.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public boolean validateLoginWeb(String usuario) {
        List<CargoPermiso> listPermisos = repository.fetchAllCargoPermisosByUsuario(usuario);
        listPermisos = listPermisos.stream().filter(cargoPermiso -> cargoPermiso.getPermiso().getId().equals(PermisoService.PERMISO_ACCESO_WEB_ID)).collect(Collectors.toList());
        return !listPermisos.isEmpty();
    }

    @Override
    public boolean validateLoginApp(String usuario) {
        List<CargoPermiso> listPermisos = repository.fetchAllCargoPermisosByUsuario(usuario);
        listPermisos = listPermisos.stream().filter(cargoPermiso -> cargoPermiso.getPermiso().getId().equals(PermisoService.PERMISO_ACCESO_APP_ID)).collect(Collectors.toList());
        return !listPermisos.isEmpty();
    }
}
