package com.example.finx.user.jwt;

import com.example.finx.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final UserService userService;

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        UserDetails userDetails = getUserDetails(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer", "");
        }
        return null;
    }

    private UserDetails getUserDetails(String id) {
        return userService.loadUserByUsername(id);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey("1234")
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}