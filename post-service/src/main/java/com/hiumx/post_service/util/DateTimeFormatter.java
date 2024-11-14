package com.hiumx.post_service.util;

import com.hiumx.post_service.exception.AppException;
import com.hiumx.post_service.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormatter {
    Map<Long, Function<Instant, String>> strategy = new LinkedHashMap<>();

    public DateTimeFormatter() {
        strategy.put(60L, this::formatSecond);
        strategy.put(3600L, this::formatMinute);
        strategy.put(86400L, this::formatHour);
        strategy.put(Long.MAX_VALUE, this::formatDate);
    }

    private String formatSecond(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return String.format("%d seconds ago", elapseSeconds);
    }

    private String formatMinute(Instant instant) {
        long elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return elapseMinutes == 1
                ? String.format("%d minute ago", elapseMinutes)
                : String.format("%d minutes ago", elapseMinutes);
    }

    private String formatHour(Instant instant) {
        long elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
        return elapseHours == 1
                ? String.format("%d hour ago", elapseHours)
                : String.format("%d hours ago", elapseHours);
    }

    private String formatDate(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;
        return dateTimeFormatter.format(localDateTime);
    }

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        return strategy.entrySet().stream()
                .filter(longFunctionEntry -> elapseSeconds < longFunctionEntry.getKey())
                .findFirst().orElseThrow(() -> new AppException(ErrorCode.TIME_FORMAT_KEY_INVALID))
                .getValue().apply(instant);
    }
}
