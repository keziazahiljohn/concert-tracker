package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    List<Artist> findByNameContainingIgnoreCase(String name);

    List<Artist> findByGenre(String genre);

    List<Artist> findByGenreIgnoreCase(String genre);
}