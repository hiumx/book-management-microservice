package com.hiumx.notification_service.controller;

import com.hiumx.event.dto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationController {

    @KafkaListener(topics = "notification-topic")
    public void listenNotification(NotificationEvent event) {
        log.info("Message: {}", event.toString());
    }
}
