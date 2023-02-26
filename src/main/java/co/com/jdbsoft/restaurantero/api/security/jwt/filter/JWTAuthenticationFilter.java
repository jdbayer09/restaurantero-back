package co.com.jdbsoft.restaurantero.api.security.jwt.filter;

import co.com.jdbsoft.restaurantero.api.models.entities.Usuario;
import co.com.jdbsoft.restaurantero.api.security.jwt.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario == null && contrasena == null) {
            try {
                Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
                usuario = user.getUsuario().trim();
                contrasena = user.getContrasena().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return authManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                contrasena
                        )
                );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String token = jwtService.createToken(authResult);
        response.addHeader(JWTService.HEADER_STRING, JWTService.TOKEN_PREFIX + token);
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", ((Usuario) authResult.getPrincipal()).getUsuario());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Usuario o contraseña no son validos!");
        body.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }


}
