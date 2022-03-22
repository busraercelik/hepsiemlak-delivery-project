package com.bsr.emlak.cloudgateway.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter {

    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final AuthenticationService authenticationService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticationFilter(AuthenticationService authenticationService, ObjectMapper objectMapper) {
        this.authenticationService = authenticationService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Received request {} : {}");
        //read token
        List<String> headerValue = exchange.getRequest().getHeaders()
                .get(AUTHENTICATION_HEADER_NAME);

        //check if token is empty
        if(CollectionUtils.isEmpty(headerValue)){
            return generateErrorResponse(String.format("Please pass %s", AUTHENTICATION_HEADER_NAME), exchange);
        }
        String authenticationHeaderValue = headerValue.stream().findAny().orElse(null);

        //verify token
        if(!authenticationService.verifyToken(authenticationHeaderValue)){
            return generateErrorResponse("Invalid token passed", exchange);
        }
        /* allow the request pass */
        return chain.filter(exchange);
    }

    private Mono<Void> generateErrorResponse(String message, ServerWebExchange exchange) throws JsonProcessingException {
        AuthenticationResponseDTO errorBody = AuthenticationResponseDTO
                .builder()
                .isSuccessfulAuthentication(false)
                .message(message)
                .build();
        return getErrorResponse(exchange, errorBody);
    }

    private Mono<Void> getErrorResponse(ServerWebExchange exchange, AuthenticationResponseDTO errorBody)
            throws JsonProcessingException {
        String errorBodyStr = objectMapper.writeValueAsString(errorBody);
        byte[] bytes = errorBodyStr.getBytes(StandardCharsets.UTF_8);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}