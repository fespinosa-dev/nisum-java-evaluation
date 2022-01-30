package com.nisum.evaluation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtTokenService implements Serializable {

    @Value("${nisum.app.token.generator-jwtExpirationMs}")
    private int jwtExpirationMs;

    private final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Optional<String> getUsernameFromToken(String token) {
        return Optional.ofNullable(getClaimFromToken(token, Claims::getSubject));
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(KEY).compact();
    }

    public Boolean validateToken(String token, String userName) {
        final var username = getUsernameFromToken(token);
        return username.map(u->u.equals(userName) && !isTokenExpired(token))
                .orElse(false);

    }
}