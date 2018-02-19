package com.urbanpawel.overtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {OvertimeApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class OvertimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OvertimeApplication.class, args);
    }
}
