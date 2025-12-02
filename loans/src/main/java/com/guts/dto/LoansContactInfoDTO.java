package com.guts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDTO(String message, HashMap<String, String> email_name, List<String> mobile_number) {
}