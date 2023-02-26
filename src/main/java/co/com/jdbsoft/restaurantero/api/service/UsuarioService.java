package co.com.jdbsoft.restaurantero.api.service;

public interface UsuarioService {


    boolean validateLoginWeb(String usuario);
    boolean validateLoginApp(String usuario);
}
