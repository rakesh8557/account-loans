package com.guts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Account details of the customer"
)
public class AccountsDTO {

    @Schema(
            description = "account number of the customer"
    )
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "account type of the customer", example = "Savings / Current"
    )
    @NotEmpty(message = "Account type should not be empty")
    private String accountType;

    @Schema(
            description = "branch address of the customer"
    )
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}
