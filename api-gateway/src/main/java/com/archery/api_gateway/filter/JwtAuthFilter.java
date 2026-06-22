package com.archery.api_gateway.filter;

import com.archery.api_gateway.service.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain
    ) {

        String path = exchange.getRequest().getURI().getPath();

        // Public endpoints
        List<String> publicPaths = List.of(
                "/auth/login",
                "/auth/register",
                "/auth/refresh"
        );

        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        Claims claims = jwtUtil.extractClaims(token);

        String cid = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(r -> r.headers(headers -> {
                    headers.add("X-User-CID", cid);
                    headers.add("X-User-Roles",roles ==null?"" :String.join(",", roles));
                }))
                .build();

        return chain.filter(mutatedExchange);
    }
}