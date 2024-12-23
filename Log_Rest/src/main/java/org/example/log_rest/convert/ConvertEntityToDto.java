package org.example.log_rest.convert;

import org.example.log_rest.dto.LogDataDto;
import org.example.log_rest.model.LogData;
import org.springframework.stereotype.Service;

@Service
public class ConvertEntityToDto {
    public LogDataDto convertLogDataToDto(LogData logData) {
        if (logData == null) {
            throw new IllegalArgumentException("logData is null");
        }
        return new LogDataDto(
                logData.getLevel().name(),
                logData.getSrc(),
                logData.getMessage()
        );
    }
}
