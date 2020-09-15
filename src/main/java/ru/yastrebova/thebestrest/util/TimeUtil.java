package ru.yastrebova.thebestrest.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Data
@Component
public class TimeUtil {
    public LocalTime now() {
        return LocalTime.now();
    }
}
