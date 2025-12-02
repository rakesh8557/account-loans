package com.guts;

import com.guts.dto.CardsContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {CardsContactInfoDTO.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsApplication {
    public static void main( String[] args ) {
        SpringApplication.run(CardsApplication.class, args);
    }
}
