package com.hiumx.identity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendEmailRequest {
    private EmailReceiver to;
    private String subject;
    private String htmlContent;
}
