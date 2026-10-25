package sn.isi.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sn.isi.authservice.entity.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security.jwt.secret}") private String secret;
    @Value("${security.jwt.expiration-seconds:3600}") private long expirationSeconds;
    @Value("${security.jwt.issuer:isi-auth-service}") private String issuer;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .issuedAt(now)
                .expiration(expiration)
                .claims(Map.of(
                        "userId", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                ))


                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

}
