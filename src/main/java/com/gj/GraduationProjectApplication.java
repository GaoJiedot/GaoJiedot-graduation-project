package com.gj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gj")
public class GraduationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProjectApplication.class, args);
    }

}
