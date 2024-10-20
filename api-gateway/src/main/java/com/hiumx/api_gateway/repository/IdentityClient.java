package com.hiumx.api_gateway.repository;

import com.hiumx.api_gateway.dto.request.VerifyTokenRequest;
import com.hiumx.api_gateway.dto.response.ApiResponse;
import com.hiumx.api_gateway.dto.response.VerifyTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<VerifyTokenResponse>> verifyToken(@RequestBody VerifyTokenRequest request);
}
