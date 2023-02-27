package co.com.jdbsoft.restaurantero.api.security.jwt.filter;

import co.com.jdbsoft.restaurantero.api.models.entities.CargoPermiso;
import co.com.jdbsoft.restaurantero.api.models.entities.Usuario;
import co.com.jdbsoft.restaurantero.api.models.enums.Plataforma;
import co.com.jdbsoft.restaurantero.api.models.requests.LoginRequest;
import co.com.jdbsoft.restaurantero.api.security.jwt.service.JWTService;
import co.com.jdbsoft.restaurantero.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final UsuarioService usuarioService;
    private final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService, UsuarioService usuarioService) {
        this.authManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest loginRequest;
        Authentication authResp;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            log.error("Error al iniciar sesión al usuario: \n" + e.getMessage());
            throw new UsernameNotFoundException("Problemas al leer los datos, verifica que todos sean correctos: ['usuario', 'contrasena', 'plataforma']");
        }
        try {
            authResp = authManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsuario(),
                                    loginRequest.getContrasena()
                            )
                    );
        } catch (AuthenticationException ae) {
            if (ae.getMessage().equals("User is disabled"))
                throw new UsernameNotFoundException("El usuario esta Deshabilitado!", ae);
            else if (ae.getMessage().equals("Bad credentials"))
                throw new UsernameNotFoundException("Usuario o contraseña incorrectos!", ae);
            else
                throw new UsernameNotFoundException("Error al ingresar tus credenciales!", ae);
        }
        if (loginRequest.getPlataforma().equals(Plataforma.WEB)){
            if (!usuarioService.validateLoginWeb(loginRequest.getUsuario()))
                throw new UsernameNotFoundException("No tienes Acceso a la plataforma Web!");
        } else if (loginRequest.getPlataforma().equals(Plataforma.APP)) {
            if (!usuarioService.validateLoginApp(loginRequest.getUsuario()))
                throw new UsernameNotFoundException("No tienes Acceso a la plataforma Aplicación móvil!");
        }
        return authResp;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        Usuario usuarioData = ((Usuario) authResult.getPrincipal());
        String token = jwtService.createToken(authResult);
        response.addHeader(JWTService.HEADER_STRING, JWTService.TOKEN_PREFIX + token);
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("id", usuarioData.getId());
        body.put("usuario", usuarioData.getUsuario());
        body.put("nombre", usuarioData.getNombres() + " " + usuarioData.getApellidos());
        body.put("cargo", usuarioData.getCargo().getNombre());
        body.put("permisos", usuarioData.getCargo().getPermisos().stream().map(cargoPermiso -> cargoPermiso.getPermiso().getId()).toList());
        log.info(body.toString());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Credenciales no validas!");
        body.put("error", failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
