package com.ktds.templify.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class JwtRequestFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 1. 토큰의 생성과 삭제를 담당하는 auth service의 api 호출 건의 경우, jwt request filter를 거치지 않도록 예외 처리.
        if (isPublicEndpoint(request.getURI().getPath())) {
            return chain.filter(exchange);
        }

        // 2. Header의 Authorization 값을 추출하여 토큰 영역 추출.
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 3. Bearer 제거하여 JWT token 추출
        String token = authHeader.substring(7);
        try {
            // 4. JWT token으로부터 claim 추출을 시도함으로써 토큰 정상 여부 판별을 동시 수행하고, claim의 userId 값을 추출 시도.
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Long userId = claims.get("userId", Long.class);
            // 5. userId가 claim에서 추출되지 못했을 경우, 비정상적인 토큰으로 간주.
            if (userId == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // 6. 초기 request를 변조시켜 x-user-id 라는 이름으로 header를 생성하고, 해당 header의 값으로 추출한 userId 삽입.
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("x-user-id", String.valueOf(userId))
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();
            return chain.filter(mutatedExchange);
        } catch (JwtException e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth") || path.startsWith("/public");
    }

    // filter의 순번 지정값. 숫자가 낮을수록 우선순위의 filter.
    @Override
    public int getOrder() {
        return -1;
    }
}
