package com.guts.contoller;

import com.guts.constants.AccountConstants;
import com.guts.dto.CustomerDTO;
import com.guts.dto.ResponseDTO;
import com.guts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Rest APIs for Accounts in Guts Bank",
        description = "This is a REST API for Accounts in Guts Bank"
    )
@RestController
@RequestMapping(value = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService accountService;

    @PostMapping("create")
    @Operation(
            summary = "Create Account",
            description = "This API is used to create an account in GUTS Bank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(201)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("getAccountDetails")
    @Operation(
            summary = "Fetch Account Details",
            description = "This API is used to fetch an account in GUTS Bank"
    )
    @ApiResponse(
            responseCode = "200"
    )
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
                                                           String mobileNumber) {
        return ResponseEntity.ok(accountService.getAccountDetails(mobileNumber));
    }

    @PutMapping("updateAccountDetails")
    @Operation(
            summary = "Update Account Details",
            description = "This API is used to update an account in GUTS Bank"
    )
    @ApiResponse(
            responseCode = "200"
    )
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("deleteAccount")
    @Operation(
            summary = "Delete Account",
            description = "This API is used to delete an account in GUTS Bank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Request processed Successfully"
    )
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
                                                     String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }

}