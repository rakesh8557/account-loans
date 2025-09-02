package com.guts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "ErrorMessageDTO",
        description = "Error message details"
)
public class ErrorMessageDTO {
    private String path;
    private HttpStatus status;
    private String message;
    private LocalDateTime time;
}
