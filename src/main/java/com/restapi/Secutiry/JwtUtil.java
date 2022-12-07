package com.restapi.Secutiry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String CLAIMS_SUBJECT="sub";
    private final String CLAIMS_CREATED="created";
    @Value("${expirationDate}")
    private Long tokenValidUntil;
    @Value("${secretKey}")
    private String tokenSecret;
    public String generateToken(MyUserDetails myUserDetails){
        Map<String,Object>claims=new HashMap<>();
        claims.put(CLAIMS_CREATED,new Date());
        claims.put(CLAIMS_SUBJECT,myUserDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,tokenSecret)
                .compact();
    }
    public String getUserNameFromToken(String token){
        try{
            Claims claims=Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }catch (Exception e){
            return null;
        }
    }
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+tokenValidUntil*1000);
    }

    public boolean isTokenValid(String token, MyUserDetails userDetails) {
        String userName=getUserNameFromToken(token);
        if(userName.equals(userDetails.getUsername())&&!expiredToken(token)){
            return true;
        }return false;
    }

    private boolean expiredToken(String token) {
        Date expiration=getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token){
        Claims claims=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
           throw new RuntimeException("Claims not found "+ claims);
        }
        return claims;
    }
}
