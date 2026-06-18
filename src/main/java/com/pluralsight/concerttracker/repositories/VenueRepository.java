package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

    List<Venue> findByNameContainingIgnoreCase(String name);

    List<Venue> findByCityIgnoreCase(String city);

    List<Venue> findByCapacityGreaterThanEqual(int capacity);

}
