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

    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
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
