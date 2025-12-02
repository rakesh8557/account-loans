package com.guts.controller;

import com.guts.constants.LoanConstants;
import com.guts.dto.LoanDTO;
import com.guts.dto.LoansContactInfoDTO;
import com.guts.dto.ResponseDTO;
import com.guts.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping(path = "api/v1/loans", produces = "application/json")
@Validated
public class LoanController {

    @Autowired
    private  ILoanService loanService;

    @Autowired
    private Environment environment;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private LoansContactInfoDTO loansContactInfoDTO;

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("fetch")
    public ResponseEntity<LoanDTO> fetchLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber) {
        return ResponseEntity.ok().body(loanService.fetchLoan(mobileNumber));
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber) {
        loanService.deleteLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoanDTO loanDTO) {
        loanService.updateLoan(loanDTO);
        return ResponseEntity.ok().body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @GetMapping("getBuildInfo")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok().body(buildVersion);
    }

    @GetMapping("java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok().body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("contact-info")
    public ResponseEntity<LoansContactInfoDTO> getContactInfo() {
        return ResponseEntity.ok().body(loansContactInfoDTO);
    }

}
