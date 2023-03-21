package com.example.productservice.util;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DateTimeProvider {
    private static final String UTC_ZONE_ID = "UTC";

    public ZonedDateTime nowUTC() {
        return ZonedDateTime.now(ZoneId.of(UTC_ZONE_ID));
    }
}
