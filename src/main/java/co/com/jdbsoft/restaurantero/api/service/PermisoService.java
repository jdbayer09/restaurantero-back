package co.com.jdbsoft.restaurantero.api.service;

import java.util.List;

public interface PermisoService {
    Long PERMISO_ACCESO_WEB_ID = 1L;
    Long PERMISO_ACCESO_APP_ID = 2L;

    List<Long> listPermisosUsuario(String usuario);
}
