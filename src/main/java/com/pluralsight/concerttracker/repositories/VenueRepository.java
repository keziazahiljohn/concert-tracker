package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    List<Venue> findByCityIgnoreCase(String city);

    List<Venue> findByNameContainingIgnoreCase(String name);

    List<Venue> findByCapacityGreaterThanEqual(int capacity);
}
