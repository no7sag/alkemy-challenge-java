package com.no7sag.alkemychallengejava.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)  // HS512
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Firma de JSON Web Token no válida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("JSON Web Token no válido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JSON Web Token expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JSON Web Token no soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JSON Web Token afirma que la cadena está vacía: {}", e.getMessage());
        }

        return false;
    }
}