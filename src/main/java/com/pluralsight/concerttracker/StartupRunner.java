package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.services.ConcertTrackerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ConcertTrackerService concertService;

    public StartupRunner(ConcertTrackerService concertService) {
        this.concertService = concertService;
    }

    @Override
    public void run(String... args) {

    }
}
