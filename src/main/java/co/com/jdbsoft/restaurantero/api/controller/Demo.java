package co.com.jdbsoft.restaurantero.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class Demo {
    private final PasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ResponseEntity<String> pass(){
        return ResponseEntity.ok(bCryptPasswordEncoder.encode("12345"));
    }


}
