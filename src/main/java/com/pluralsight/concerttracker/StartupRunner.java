package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.services.ConcertTrackerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ConcertTrackerService concertService;

    public StartupRunner(ConcertTrackerService concertService) {
        this.concertService = concertService;
    }

    @Override
    public void run(String... args) {
        concertService.seedIfEmpty();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Concert Tracker ===");
            System.out.println("1) List All Concerts");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> listConcerts();
                case 0 -> running = false;
                default -> System.out.println("Unknown option");
            }
        }
    }

    private void listConcerts() {
        for (Concert concert : concertService.getAllConcerts()) {
            System.out.printf(
                    "%d | %s | %s | $%.2f%n",
                    concert.getConcertYear(),
                    concert.getArtist().getName(),
                    concert.getVenue().getName(),
                    concert.getTicketPrice());
        }
    }
}
