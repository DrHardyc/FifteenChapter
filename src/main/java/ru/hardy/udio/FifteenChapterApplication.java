package ru.hardy.udio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FifteenChapterApplication {

    public static void main(String[] args) {

        SpringApplication.run(FifteenChapterApplication.class, args);

    }
}