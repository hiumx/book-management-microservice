package com.hiumx.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEvent {
    private String chanel;
    private String receiver;
    private String templateCode;
    private Map<String, Objects> params;
    private String subject;
    private String content;
}
