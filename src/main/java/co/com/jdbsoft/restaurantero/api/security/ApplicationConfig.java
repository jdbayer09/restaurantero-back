package co.com.jdbsoft.restaurantero.api.security;

import co.com.jdbsoft.restaurantero.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsuarioRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}