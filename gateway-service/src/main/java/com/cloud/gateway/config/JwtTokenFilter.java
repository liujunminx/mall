package com.cloud.gateway.config;

import com.cloud.gateway.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class JwtTokenFilter implements GlobalFilter {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String tokenKey = "token";
        String uri = request.getURI().getPath();
        if (uri.contains("/user/auth/signIn") || uri.contains("/user/auth/authenticate")) {
            return chain.filter(exchange);
        }
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (cookies.containsKey(tokenKey)) {
            String token = cookies.get(tokenKey).get(0).getValue();
            if (jwtUtil.validateToken(token) == null)
                return getVoidMono(response);
            else
                return chain.filter(exchange);
        }
        return getVoidMono(response);
    }

    private Mono<Void> getVoidMono(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(response.bufferFactory().wrap("{ \"error\": \"Unauthorized\", \"message\": \"Token validation failed\" }".getBytes())));
    }
}
