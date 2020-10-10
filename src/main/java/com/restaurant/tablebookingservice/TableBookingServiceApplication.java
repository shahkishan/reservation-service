package com.restaurant.tablebookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TableBookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableBookingServiceApplication.class, args);
    }

}
