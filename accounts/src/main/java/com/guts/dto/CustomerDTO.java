package com.guts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Customer details"
)
@Data
public class CustomerDTO {

    @Schema(
            description = "name of the customer", example = "John Doe"
    )
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "email of the customer", example = "john.doe@.com"
    )
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "mobile number of the customer", example = "1234567890"
    )
    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "account details of the customer"
    )
    private AccountsDTO accountDetails;
}
