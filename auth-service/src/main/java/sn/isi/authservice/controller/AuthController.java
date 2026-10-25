package sn.isi.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.isi.authservice.dto.AuthResponse;
import sn.isi.authservice.dto.LoginRequest;
import sn.isi.authservice.dto.RegisterRequest;
import sn.isi.authservice.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest r) {

        log.info("Requête reçue pour l'inscription d'un utilisateur");

        AuthResponse response = authService.register(r);

        log.info("Inscription effectuée avec succès");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest r) {

        log.info("Requête reçue pour la connexion d'un utilisateur");

        AuthResponse response = authService.login(r);

        log.info("Connexion effectuée avec succès");

        return ResponseEntity.ok(response);
    }
}