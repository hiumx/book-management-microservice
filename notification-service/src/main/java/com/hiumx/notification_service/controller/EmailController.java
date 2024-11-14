package com.hiumx.notification_service.controller;

import com.hiumx.notification_service.dto.request.SendEmailRequest;
import com.hiumx.notification_service.dto.response.ApiResponse;
import com.hiumx.notification_service.dto.response.EmailResponse;
import com.hiumx.notification_service.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailController {

    EmailService emailService;

    @PostMapping("/email")
    ApiResponse<EmailResponse> sendEmail(SendEmailRequest request) {
        log.info("Send email request: {}", request.toString());
        return ApiResponse.<EmailResponse>builder()
                .code(1000)
                .message("Send email successfully")
                .result(emailService.sendEmail(request))
                .build();
    }

}
