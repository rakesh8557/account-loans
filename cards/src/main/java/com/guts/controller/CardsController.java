package com.guts.controller;

import com.guts.constants.CardsConstants;
import com.guts.dto.CardsContactInfoDTO;
import com.guts.dto.CardsDTO;
import com.guts.dto.ResponseDTO;
import com.guts.service.CardService;
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
@RequestMapping("/api/v1/cards")
@Validated
public class CardsController {

    @Autowired
    private CardService cardService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDTO cardsContactInfoDTO;

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @GetMapping("fetch")
    public ResponseEntity<CardsDTO> getCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber) {
        return ResponseEntity.ok().body(cardService.getCard(mobileNumber));
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number") String mobileNumber, @RequestParam String cardNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber, cardNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardsDTO cardsDTO) {
        boolean isUpdated = cardService.updateCard(cardsDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(200)
                    .body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(417)
                    .body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
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
    public ResponseEntity<CardsContactInfoDTO> getContactInfo() {
        return ResponseEntity.ok().body(cardsContactInfoDTO);
    }
}
