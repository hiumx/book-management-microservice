package com.hiumx.api_gateway.service;

import com.hiumx.api_gateway.dto.request.VerifyTokenRequest;
import com.hiumx.api_gateway.dto.response.ApiResponse;
import com.hiumx.api_gateway.dto.response.VerifyTokenResponse;
import com.hiumx.api_gateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<VerifyTokenResponse>> verifyToken(String token) {
        return identityClient.verifyToken(VerifyTokenRequest.builder().token(token).build());
    }
}
