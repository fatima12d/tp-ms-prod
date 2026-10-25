package sn.isi.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.isi.authservice.dto.AuthResponse;
import sn.isi.authservice.dto.LoginRequest;
import sn.isi.authservice.dto.RegisterRequest;
import sn.isi.authservice.entity.Role;
import sn.isi.authservice.entity.User;
import sn.isi.authservice.exceptions.BadRequestException;
import sn.isi.authservice.exceptions.DuplicateUserException;
import sn.isi.authservice.exceptions.InvalidCredentialsException;
import sn.isi.authservice.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest r) {
        log.info("Début de l'inscription pour le username : {}", r.getUsername());

        if (userRepository.existsByUsername(r.getUsername())) {
            log.warn("Échec de l'inscription : le username {} existe déjà", r.getUsername());
            throw new DuplicateUserException("...");
        }

        log.debug("Encodage du mot de passe pour le nouvel utilisateur");

        User user = User.builder()
                .username(r.getUsername())
                .password(passwordEncoder.encode(r.getPassword()))
                .email(r.getEmail())
                .role(Role.USER)
                .build();

        User saved = userRepository.save(user);

        log.info("Utilisateur enregistré avec succès : {}", saved.getUsername());

        String token = jwtService.generateToken(saved);

        log.info("Jeton JWT généré pour l'utilisateur : {}", saved.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(saved.getUsername())
                .email(saved.getEmail())
                .role(saved.getRole().name())
                .build();
    }

    public AuthResponse login(LoginRequest r) {
        log.info("Tentative de connexion pour le username : {}", r.getUsername());

        User user = userRepository.findByUsername(r.getUsername())
                .orElseThrow(() -> {
                    log.warn("Échec de connexion : utilisateur introuvable");
                    return new BadRequestException("Invalides");
                });

        if (!passwordEncoder.matches(r.getPassword(), user.getPassword())) {
            log.warn("Échec de connexion : mot de passe incorrect pour {}", r.getUsername());
            throw new InvalidCredentialsException("Invalides");
        }

        log.info("Authentification réussie pour l'utilisateur : {}", user.getUsername());

        String token = jwtService.generateToken(user);

        log.debug("Jeton JWT généré avec succès");

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}