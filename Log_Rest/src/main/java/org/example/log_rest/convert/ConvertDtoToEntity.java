package org.example.log_rest.convert;

import org.example.log_rest.dto.LogDataDto;
import org.example.log_rest.enums.LogLevel;
import org.example.log_rest.model.LogData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ConvertDtoToEntity {
    public LogData converDtoToLogData(LogDataDto logDataDto) {
        String levelStr = logDataDto.getLevel().toUpperCase();
        if (!Arrays.stream(LogLevel.values()).map(Enum::name).collect(Collectors.toSet()).contains(levelStr)) {
            throw new IllegalArgumentException("Invalid log level: " + logDataDto.getLevel());
        }

        LogLevel level = LogLevel.valueOf(levelStr);
        return new LogData(0, level, logDataDto.getSrc(), logDataDto.getMessage());
    }
}
