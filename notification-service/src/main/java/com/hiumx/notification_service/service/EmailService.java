package com.hiumx.notification_service.service;

import com.hiumx.notification_service.dto.request.EmailReceiver;
import com.hiumx.notification_service.dto.request.EmailRequest;
import com.hiumx.notification_service.dto.request.EmailSender;
import com.hiumx.notification_service.dto.request.SendEmailRequest;
import com.hiumx.notification_service.dto.response.EmailResponse;
import com.hiumx.notification_service.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailService {

    EmailClient emailClient;

    @NonFinal
    @Value("${app.email.api-key}")
    String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        String subject = "Register successfully";
        String htmlContent =
                "<h1>Welcome to Book Microservice system</h1></br><p style='color: blue;'>This is content of message</p>";
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(
                        EmailSender.builder()
                                .email("service.hiumx@gmail.com")
                                .name("maixuanhieu")
                                .build()
                )
                .to(Set.of(request.getTo()))
                .subject(subject)
                .htmlContent(htmlContent)
                .build();

        log.info(request.toString());
        log.info("API KEY: {}", apiKey);
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new RuntimeException(e);
        }
    }
}
