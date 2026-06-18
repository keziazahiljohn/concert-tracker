package com.pluralsight.concerttracker;

import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
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

        while (running) {
            printMenu();

            try {
                switch (scanner.nextInt()) {
                    case 1 -> concertMenu(scanner);
                    case 2 -> artistMenu(scanner);
                    case 3 -> venueMenu(scanner);
                    case 4 -> promoterMenu(scanner);
                    case 0 -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Concerts ===");
        System.out.println("1) List All Concerts");
        System.out.println("2) View Concert By Id");
        System.out.println("3) Add Concert");
        System.out.println("4) Update Ticket Price");
        System.out.println("5) Update Tickets Sold");
        System.out.println("6) Delete Concert");
        System.out.println("0) Quit");
        System.out.print("Choose: ");
    }

    private void concertMenu(Scanner scanner)
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Concerts ===");
            System.out.println("1) List All Concerts");
            System.out.println("2) View Concert By Id");
            System.out.println("3) Add Concert");
            System.out.println("4) Update Ticket Price");
            System.out.println("5) Update Tickets Sold");
            System.out.println("6) Delete Concert");
            System.out.println("0) Back");

            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> listConcerts();
                case 2 -> viewConcertById(scanner);
                case 3 -> addConcert(scanner);
                case 4 -> updatePrice(scanner);
                case 5 -> updateTicketsSold(scanner);
                case 6 -> deleteConcert(scanner);
                case 0 -> running = false;
            }
        }
    }

    private void artistMenu(Scanner scanner)
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Artists ===");
            System.out.println("1) List Artists");
            System.out.println("2) Add Artist");
            System.out.println("3) Search By Genre");
            System.out.println("4) Search By Name");
            System.out.println("5) Delete Artist");
            System.out.println("0) Back");

            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> {
                    for(Artist artist : concertService.getAllArtists())
                    {
                        System.out.println(
                                artist.getId() +
                                        " - " +
                                        artist.getName() +
                                        " (" +
                                        artist.getGenre() +
                                        ")");
                    }
                }

                case 2 -> {
                    scanner.nextLine();

                    System.out.print("Artist name: ");
                    String name = scanner.nextLine();

                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    concertService.addArtist(name, genre);

                    System.out.println("Artist added.");
                }

                case 3 -> {
                    scanner.nextLine();

                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    for(Artist artist :
                            concertService.findArtistsByGenre(genre))
                    {
                        System.out.println(artist.getName());
                    }
                }

                case 4 -> {
                    scanner.nextLine();

                    System.out.print("Name contains: ");
                    String name = scanner.nextLine();

                    for(Artist artist :
                            concertService.findArtistsByName(name))
                    {
                        System.out.println(artist.getName());
                    }
                }

                case 5 -> {
                    System.out.print("Artist id: ");
                    long id = scanner.nextLong();

                    concertService.deleteArtist(id);

                    System.out.println("Artist deleted.");
                }

                case 0 -> running = false;
            }
        }
    }

    private void venueMenu(Scanner scanner)
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Venues ===");
            System.out.println("1) List Venues");
            System.out.println("2) Add Venue");
            System.out.println("3) Search By City");
            System.out.println("4) Search By Name");
            System.out.println("5) Search By Minimum Capacity");
            System.out.println("6) Delete Venue");
            System.out.println("0) Back");

            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> {
                    for(Venue venue : concertService.getAllVenues())
                    {
                        System.out.println(
                                venue.getId() +
                                        " - " +
                                        venue.getName() +
                                        " (" +
                                        venue.getCity() +
                                        ")");
                    }
                }

                case 2 -> {
                    scanner.nextLine();

                    System.out.print("Venue name: ");
                    String name = scanner.nextLine();

                    System.out.print("City: ");
                    String city = scanner.nextLine();

                    System.out.print("Capacity: ");
                    int capacity = scanner.nextInt();

                    concertService.addVenue(
                            name,
                            city,
                            capacity);

                    System.out.println("Venue added.");
                }

                case 3 -> {
                    scanner.nextLine();

                    System.out.print("City: ");
                    String city = scanner.nextLine();

                    for(Venue venue :
                            concertService.findVenueByCity(city))
                    {
                        System.out.println(venue.getName());
                    }
                }

                case 4 -> {
                    scanner.nextLine();

                    System.out.print("Name contains: ");
                    String name = scanner.nextLine();

                    for(Venue venue :
                            concertService.findVenueByName(name))
                    {
                        System.out.println(venue.getName());
                    }
                }

                case 5 -> {
                    System.out.print("Minimum capacity: ");
                    int capacity = scanner.nextInt();

                    for(Venue venue :
                            concertService.findVenueByMinimumCapacity(capacity))
                    {
                        System.out.println(
                                venue.getName()
                                        + " - "
                                        + venue.getCapacity());
                    }
                }

                case 6 -> {
                    System.out.print("Venue id: ");
                    long id = scanner.nextLong();

                    concertService.deleteVenue(id);

                    System.out.println("Venue deleted.");
                }

                case 0 -> running = false;
            }
        }
    }private void promoterMenu(Scanner scanner)
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== Promoters ===");
            System.out.println("1) List Promoters");
            System.out.println("2) Add Promoter");
            System.out.println("3) Search By Name");
            System.out.println("4) Delete Promoter");
            System.out.println("0) Back");

            int choice = scanner.nextInt();

            switch(choice)
            {
                case 1 -> {
                    for(Promoter promoter :
                            concertService.getAllPromoters())
                    {
                        System.out.println(
                                promoter.getId()
                                        + " - "
                                        + promoter.getName());
                    }
                }

                case 2 -> {
                    scanner.nextLine();

                    System.out.print("Promoter name: ");
                    String name = scanner.nextLine();

                    concertService.addPromoter(name);

                    System.out.println("Promoter added.");
                }

                case 3 -> {
                    scanner.nextLine();

                    System.out.print("Name contains: ");
                    String name = scanner.nextLine();

                    for(Promoter promoter :
                            concertService.findPromotersByName(name))
                    {
                        System.out.println(promoter.getName());
                    }
                }

                case 4 -> {
                    System.out.print("Promoter id: ");
                    long id = scanner.nextLong();

                    concertService.deletePromoter(id);

                    System.out.println("Promoter deleted.");
                }

                case 0 -> running = false;
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

    private void viewConcertById(Scanner scanner) {
        System.out.print("Concert id: ");
        long id = scanner.nextLong();
        Concert concert = concertService.getConcertById(id);

        System.out.println("\n=== Concert Details ===");
        System.out.println("Artist: " + concert.getArtist().getName());
        System.out.println("Venue: " + concert.getVenue().getName());
        System.out.println("Promoter: " + concert.getPromoter().getName());
        System.out.println("Year: " + concert.getConcertYear());
        System.out.println("Price: $" + concert.getTicketPrice());
        System.out.println("Tickets Sold: " + concert.getTicketsSold());
    }

    private void addConcert(Scanner scanner) {
        System.out.println("\nArtists");

        for(Artist artist : concertService.getAllArtists()) {
            System.out.println(artist.getId() + " - " + artist.getName());
        }

        System.out.print("Artist id: ");
        long artistId = scanner.nextLong();

        System.out.println("\nVenues");

        for(Venue venue : concertService.getAllVenues()) {
            System.out.println(venue.getId() + " - " + venue.getName());
        }

        System.out.print("Venue id: ");
        long venueId = scanner.nextLong();

        System.out.println("\nPromoters");

        for(Promoter promoter : concertService.getAllPromoters()) {
            System.out.println(
                    promoter.getId()
                            + " - "
                            + promoter.getName());
        }

        System.out.print("Promoter id: ");
        long promoterId = scanner.nextLong();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Ticket price: ");
        double price = scanner.nextDouble();

        System.out.print("Tickets sold: ");
        int ticketsSold = scanner.nextInt();

        concertService.addConcert(artistId, venueId, promoterId, year, price, ticketsSold);
        System.out.println("Concert added.");
    }

    private void updatePrice(Scanner scanner) {
        System.out.print("Concert id: ");
        long id = scanner.nextLong();

        System.out.print("New ticket price: ");
        double price = scanner.nextDouble();

        concertService.updateConcertPrice(id, price);
        System.out.println("Concert updated.");
    }

    private void updateTicketsSold(Scanner scanner) {
        System.out.print("Concert id: ");
        long id = scanner.nextLong();

        System.out.print("New tickets sold count: ");
        int sold = scanner.nextInt();

        concertService.updateTicketsSold(id, sold);
        System.out.println("Concert updated.");
    }

    private void deleteConcert(Scanner scanner) {
        System.out.print("Concert id: ");
        long id = scanner.nextLong();

        concertService.deleteConcert(id);
        System.out.println("Concert deleted.");
    }
}
