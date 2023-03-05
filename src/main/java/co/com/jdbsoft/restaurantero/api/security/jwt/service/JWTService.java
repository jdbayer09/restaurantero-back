package co.com.jdbsoft.restaurantero.api.security.jwt.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    String createToken(Authentication auth) throws IOException;
    boolean validateToken(String token);
    String getUsuario(String token);
    Collection<SimpleGrantedAuthority> getRoles(String token) throws IOException;
}
