package org.example.log_rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDataDto {
    private String level;
    private String src;
    private String message;
}