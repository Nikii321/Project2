package com.example.untitled13.config.filter;

import com.example.untitled13.service.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTVerifierFilter extends OncePerRequestFilter {
    private JWTUtils jwtUtils;

    public JWTVerifierFilter(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken == null || !jwtUtils.validateToken(bearerToken)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        Claims authClaim = jwtUtils.getAllClaimsFromToken(bearerToken);

        String username = authClaim.getId();
        String password = authClaim.getSubject();

        List<String> authorities = (List<String>) authClaim.get("role");
        List<GrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        httpServletRequest.setAttribute("username", username);
        httpServletRequest.setAttribute("authorities", grantedAuthorities);
        httpServletRequest.setAttribute("jwt", bearerToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
