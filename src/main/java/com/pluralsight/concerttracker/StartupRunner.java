package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.services.ConcertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ConcertService concertService;

    public StartupRunner(ConcertService concertService) {
        this.concertService = concertService;
    }

    @Override
    public void run(String... args) {

    }
}
