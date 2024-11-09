package com.hiumx.notification_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {
    private EmailSender sender;
    private Set<EmailReceiver> to;
    private String subject;
    private String htmlContent;
}
