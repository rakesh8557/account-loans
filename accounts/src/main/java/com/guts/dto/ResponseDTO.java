package com.guts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold the successful response"
)
public class ResponseDTO {
    @Schema(
            description = "status of the response", example = "200"
    )
    private String status;

    @Schema(
            description = "message of the response", example = "Created Successfully"
    )
    private String message;
}
