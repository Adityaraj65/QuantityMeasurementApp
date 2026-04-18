package com.app.gateway_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.app.gateway_service.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // ✅ PUBLIC APIs (no login required)
        if (path.startsWith("/auth") ||
            path.contains("/add") ||
            path.contains("/subtract") ||
            path.contains("/multiply") ||
            path.contains("/divide") ||
            path.contains("/convert") ||
            path.contains("/compare")) {

            return chain.filter(exchange);
        }

        // 🔐 PROTECTED (history)
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new RuntimeException("Login required to access history");
        }

        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        jwtUtil.validateToken(token);
        String email = jwtUtil.extractEmail(token);

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("user-email", email)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}