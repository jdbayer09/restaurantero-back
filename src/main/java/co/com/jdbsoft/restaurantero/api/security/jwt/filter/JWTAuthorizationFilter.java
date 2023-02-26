package co.com.jdbsoft.restaurantero.api.security.jwt.filter;

import co.com.jdbsoft.restaurantero.api.security.jwt.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (!requiresAuthentication(header)) {
            chain.doFilter(request, response);
            return;
        }

        if (jwtService.validateToken(header)) {
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                jwtService.getUsuario(header),
                        null,
                                jwtService.getRoles(header)
                            )
                    );
            chain.doFilter(request, response);
        }
    }

    private boolean requiresAuthentication(String header) {
        return header != null && header.startsWith(JWTService.TOKEN_PREFIX);
    }
}
