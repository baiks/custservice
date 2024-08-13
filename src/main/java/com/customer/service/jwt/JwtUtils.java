package com.customer.service.jwt;

import com.customer.service.exception.CustomException;
import com.customer.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secretKey}")
    public String jwtSecretKey;

    @Value("${jwt.jwtValidityPeriodInMilliSecs}")
    public int jwtValidityPeriodInMilliSecs;


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtValidityPeriodInMilliSecs))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public int getTokenValidityInSecs() {
        return jwtValidityPeriodInMilliSecs / 1000;

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    @SneakyThrows
    public boolean validateJwtToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new CustomException("Sorry, the provided token is invalid.");
        } catch (ExpiredJwtException e) {
            String tokenExpiry = StringUtils.substringBetween(e.getMessage(), "JWT expired at", ". Current time");
            String tokenExpiryFormatted = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
                    .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(tokenExpiry.trim()));
            throw new CustomException(String.format("Sorry, your token expired on %s. Please, login again to get a valid token.",
                    tokenExpiryFormatted));
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw new CustomException("Sorry, the provided token is unsupported.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw new CustomException("Sorry, the provided token has no claims.");
        }
    }

}
