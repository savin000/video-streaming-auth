package ru.savin.videostreamingauth.util;

import io.jsonwebtoken.Claims;
import ru.savin.videostreamingauth.entity.JwtAuthentication;

public class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }
}
