package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
