package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
