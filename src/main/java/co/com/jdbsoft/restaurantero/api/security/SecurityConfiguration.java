package co.com.jdbsoft.restaurantero.api.security;

import co.com.jdbsoft.restaurantero.api.security.jwt.filter.JWTAuthenticationFilter;
import co.com.jdbsoft.restaurantero.api.security.jwt.filter.JWTAuthorizationFilter;
import co.com.jdbsoft.restaurantero.api.security.jwt.service.JWTService;
import co.com.jdbsoft.restaurantero.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JWTService jwtService;
    private final UsuarioService usuarioService;
    private final AuthenticationConfiguration authConfig;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*","http://localhost:4200", "https://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] freePaths = {
                "/demo"
        };
        httpSecurity
                .cors()
                    .configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(freePaths)
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authConfig.getAuthenticationManager(), jwtService, usuarioService))
                .addFilter(new JWTAuthorizationFilter(authConfig.getAuthenticationManager(), jwtService))
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();
    }
}
