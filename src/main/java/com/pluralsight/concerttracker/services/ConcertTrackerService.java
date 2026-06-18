package com.pluralsight.concerttracker.services;

import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import com.pluralsight.concerttracker.repositories.ArtistRepository;
import com.pluralsight.concerttracker.repositories.ConcertRepository;
import com.pluralsight.concerttracker.repositories.PromoterRepository;
import com.pluralsight.concerttracker.repositories.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertTrackerService {

    private final ConcertRepository concertRepository;
    private final ArtistRepository artistRepository;
    private final PromoterRepository promoterRepository;
    private final VenueRepository venueRepository;

    public ConcertTrackerService(ConcertRepository concertRepository,
                                 ArtistRepository artistRepository,
                                 PromoterRepository promoterRepository,
                                 VenueRepository venueRepository) {
        this.concertRepository = concertRepository;
        this.artistRepository = artistRepository;
        this.promoterRepository = promoterRepository;
        this.venueRepository = venueRepository;
    }

    // Get All

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    public List<Promoter> getAllPromoters() {
        return promoterRepository.findAll();
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // Manage Concerts

    public Concert getConcertById(long id)
    {
        return concertRepository.findById((int) id).orElseThrow(() -> new RuntimeException("Concert not found."));
    }

    public void addConcert(long artistId, long venueId, long promoterId, int year, double ticketPrice, int ticketsSold) {
        Artist artist = artistRepository.findById((int) artistId)
                .orElseThrow(() ->
                        new RuntimeException("Artist not found."));

        Venue venue = venueRepository.findById((int) venueId)
                .orElseThrow(() ->
                        new RuntimeException("Venue not found."));

        Promoter promoter = promoterRepository.findById((int) promoterId)
                .orElseThrow(() ->
                        new RuntimeException("Promoter not found."));

        if(ticketPrice < 0) {
            throw new RuntimeException(
                    "Ticket price cannot be negative.");
        }

        if(ticketsSold < 0) {
            throw new RuntimeException(
                    "Tickets sold cannot be negative.");
        }

        if(ticketsSold > venue.getCapacity()) {
            throw new RuntimeException(
                    "Tickets sold exceeds venue capacity.");
        }

        Concert concert = new Concert(
                year,
                ticketPrice,
                ticketsSold,
                artist,
                venue,
                promoter);

        concertRepository.save(concert);
    }

    public void updateConcertPrice(long id, double price)
    {
        Concert concert = concertRepository.findById((int) id)
                .orElseThrow(() ->
                        new RuntimeException("Concert not found."));

        if(price < 0)
        {
            throw new RuntimeException(
                    "Price cannot be negative.");
        }

        concert.setTicketPrice(price);

        concertRepository.save(concert);
    }

    public void updateTicketsSold(long id, int ticketsSold)
    {
        Concert concert = concertRepository.findById((int) id)
                .orElseThrow(() ->
                        new RuntimeException("Concert not found."));

        if(ticketsSold < 0)
        {
            throw new RuntimeException(
                    "Tickets sold cannot be negative.");
        }

        if(ticketsSold > concert.getVenue().getCapacity())
        {
            throw new RuntimeException(
                    "Tickets sold exceeds venue capacity.");
        }

        concert.setTicketsSold(ticketsSold);

        concertRepository.save(concert);
    }

    public void deleteConcert(long id)
    {
        if(!concertRepository.existsById((int) id))
        {
            throw new RuntimeException(
                    "Concert not found.");
        }

        concertRepository.deleteById((int) id);
    }

    // Manage Artists

    public void addArtist(String name, String genre) {
        artistRepository.save(new Artist(name, genre));
    }

    public void deleteArtist(long id) {
        artistRepository.deleteById((int) id);
    }

    public List<Artist> findArtistsByGenre(String genre) {
        return artistRepository.findByGenre(genre);
    }

    public List<Artist> findArtistsByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    // Manage Promoters

    public void addPromoter(String name) {
        promoterRepository.save(
                new Promoter(name));
    }

    public void deletePromoter(long id) {
        promoterRepository.deleteById((int) id);
    }

    public List<Promoter> findPromotersByName(String name) {
        return promoterRepository
                .findByNameContainingIgnoreCase(name);
    }

    // Manage Venues
    public void addVenue(String name, String city, int capacity) {
        venueRepository.save(new Venue(name, city, capacity));
    }

    public void deleteVenue(long id) {
        venueRepository.deleteById((int) id);
    }

    public List<Venue> findVenueByCity(String city) {
        return venueRepository.findByCityIgnoreCase(city);
    }

    public List<Venue> findVenueByName(String name) {
        return venueRepository
                .findByNameContainingIgnoreCase(name);
    }

    public List<Venue> findVenueByMinimumCapacity(int capacity) {
        return venueRepository
                .findByCapacityGreaterThanEqual(capacity);
    }




    public List<Concert> findConcertsByYear(int year){
        return concertRepository.findByYear(year);
    }

    public List<Concert> findConcertsByArtist(String name){
        return concertRepository.findByArtistName(name);
    }


    public List<Concert> findConcertsByVenue(String name){
        return concertRepository.findByVenueName(name);
    }


    public List<Concert> findConcertsByCity(String city){
        return concertRepository.findByCity(city);
    }


    public List<Concert> findConcertsByMaximumPrice(double price){
        return concertRepository.findByMaximumPrice(price);
    }


    public List<Concert> findConcertsByPriceRange(double min, double max){
        return concertRepository.findByPriceRange(min, max);
    }


    public List<Concert> findConcertsByPriceAndYear(double price, int year){
        return concertRepository.findByPriceAndYear(price, year);
    }




    public void seedIfEmpty() {
        if (concertRepository.count() > 0) {
            return;
        }

        // Artists
        Artist upperroom = artistRepository.save(new Artist("UPPERROOM", "worship"));
        Artist bethelMusic = artistRepository.save(new Artist("Bethel Music", "worship"));
        Artist elevationRhythm = artistRepository.save(new Artist("ELEVATION RHYTHM", "worship"));
        Artist elevationWorship = artistRepository.save(new Artist("Elevation Worship", "worship"));

        // Promoters
        Promoter awakenEvents = promoterRepository.save(new Promoter("Awaken Events"));
        Promoter transparentProductions = promoterRepository.save(new Promoter("Transparent Productions"));
        Promoter premierProductions = promoterRepository.save(new Promoter("Premier Productions"));

        // Venues
        Venue wellsFargo = venueRepository.save(new Venue("Wells Fargo Center", "Philadelphia", 21000));
        Venue prudentialCenter = venueRepository.save(new Venue("Prudential Center", "Newark", 19500));
        Venue giantCenter = venueRepository.save(new Venue("Giant Center", "Hershey", 10500));
        Venue cureArena = venueRepository.save(new Venue("CURE Insurance Arena", "Trenton", 10000));

        // Concerts
        concertRepository.save(
                new Concert(2024, 49.99, 9500,
                        upperroom,
                        cureArena,
                        awakenEvents));

        concertRepository.save(
                new Concert(2024, 59.99, 18000,
                        elevationWorship,
                        wellsFargo,
                        premierProductions));

        concertRepository.save(
                new Concert(2024, 45.99, 8500,
                        bethelMusic,
                        giantCenter,
                        transparentProductions));

        concertRepository.save(
                new Concert(2024, 39.99, 7000,
                        elevationRhythm,
                        cureArena,
                        awakenEvents));

        concertRepository.save(
                new Concert(2025, 69.99, 19000,
                        elevationWorship,
                        prudentialCenter,
                        premierProductions));

        concertRepository.save(
                new Concert(2025, 54.99, 9000,
                        upperroom,
                        giantCenter,
                        awakenEvents));

        concertRepository.save(
                new Concert(2025, 64.99, 17000,
                        bethelMusic,
                        wellsFargo,
                        transparentProductions));

        concertRepository.save(
                new Concert(2025, 42.99, 8200,
                        elevationRhythm,
                        cureArena,
                        premierProductions));

        concertRepository.save(
                new Concert(2026, 74.99, 20000,
                        elevationWorship,
                        wellsFargo,
                        awakenEvents));

        concertRepository.save(
                new Concert(2026, 59.99, 9500,
                        upperroom,
                        giantCenter,
                        transparentProductions));

        concertRepository.save(
                new Concert(2026, 67.99, 18000,
                        bethelMusic,
                        prudentialCenter,
                        premierProductions));

        concertRepository.save(
                new Concert(2026, 49.99, 8800,
                        elevationRhythm,
                        cureArena,
                        awakenEvents));
    }
}
