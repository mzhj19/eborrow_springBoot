package com.mzhj19.eborrow.configuration.security;

import com.mzhj19.eborrow.exception.EborrowAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

/*    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }*/

/*    private Key key(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }*/


    private Key key(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // get username from Jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "INVALID JWT TOKEN");
        } catch (ExpiredJwtException ex) {
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "EXPIRED JWT TOKEN");
        } catch (UnsupportedJwtException ex) {
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "UNSUPPORTED JWT TOKEN");
        } catch (IllegalArgumentException ex) {
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "JWT CLAIMS STRING IS EMPTY");
        } catch (SignatureException ex) {
            throw new EborrowAPIException(HttpStatus.BAD_REQUEST.value(), "JWT CLAIMS STRING IS EMPTY");
        }
    }
}
