package com.shao.file_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shao.file_service.model.User;

import org.springframework.stereotype.Service;

/**
 * token 下发
 */
@Service("TokenService")
public class TokenService {
    
    public  String getToken(final User user) {
        String token="";
        token= JWT.create().withAudience(user.getUserId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}