package co.com.jdbsoft.restaurantero.api.models.requests;

import co.com.jdbsoft.restaurantero.api.models.enums.Plataforma;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String login;
    private String contrasena;
    private Plataforma plataforma;
}
