package com.htv.config;

import io.smallrye.jwt.auth.principal.DefaultJWTParser;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Slf4j
@ApplicationScoped
public class TokenProvider {
    
    @ConfigProperty(name = "app.jwt.issuer", defaultValue = "https://jaxtony.store")
    String issuer;

    @ConfigProperty(name = "app.jwt.audience", defaultValue = "x-cv")
    String audience;

    @ConfigProperty(name = "app.jwt.expiration-minutes", defaultValue = "30")
    long expirationMinutes;

    @ConfigProperty(name = "app.jwt.refresh-expiration-minutes", defaultValue = "1440") // 24h
    long refreshExpirationMinutes;

    // Key location (hỗ trợ mp.jwt.verify.publickey.location hoặc riêng cho sign)
    @ConfigProperty(name = "app.jwt.private-key-location") // classpath:privateKey.pem
            String privateKeyLocation;

    @ConfigProperty(name = "app.jwt.public-key-location")  // classpath:publicKey.pem
    String publicKeyLocation;

    /**
     * Tạo Access Token
     */
    public String generateAccessToken(String subject, Set<String> roles, Set<String> permissions) {
        return buildToken(subject, roles, permissions, Duration.ofMinutes(expirationMinutes));
    }

    /**
     * Tạo Refresh Token (thường dài hơn)
     */
    public String generateRefreshToken(String subject) {
        return Jwt.issuer(issuer)
                .subject(subject)
                .expiresAt(Instant.now().plus(Duration.ofMinutes(refreshExpirationMinutes)))
                .sign(privateKeyLocation); // tự động detect PEM, JWK, etc.
    }

    /**
     * Validate token (dùng cho interceptor, guard, etc.)
     */
    public boolean validateToken(String token) {
        try {
            // SmallRye tự validate: signature + exp + nbf + iss + aud
            JWTParser parser = new DefaultJWTParser();
            parser.parse(token); // nếu không ném exception → token hợp lệ
            return true;
        } catch (Exception e) {
            log.debug("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Lấy subject (userId/email) từ token đã validate
     */
    public String getSubject(String token) {
        try {
            return new DefaultJWTParser().parse(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    // ============================= PRIVATE =============================

    private String buildToken(String subject, Set<String> roles, Set<String> permissions, Duration expiresIn) {
        Instant now = Instant.now();

        JwtClaimsBuilder claims = Jwt.claims()
                .issuer(issuer)
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(expiresIn))
                .audience(audience)
                .groups(roles); // roles → claim "groups" hoặc "realm_access.roles"

        // Thêm permissions vào claim tùy chỉnh
        if (permissions != null && !permissions.isEmpty()) {
            claims.claim("permissions", permissions);
        }

        // Dùng private key để sign (hỗ trợ RSA/EC 2048+ hoặc HMAC)
        return claims.jws().sign(privateKeyLocation);
    }

}
