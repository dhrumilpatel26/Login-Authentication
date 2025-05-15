package dmw.auth.JwtAuth.signin.service;

import io.jsonwebtoken.Claims;
import dmw.auth.JwtAuth.signin.entities.UserEntity;

import java.util.HashMap;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserEntity userDetails, HashMap<String,Object> claims);

//    boolean isTokenValid(String token, UserDetails userDetails);
    Claims extractAllClaims(String token);
}
