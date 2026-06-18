package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {


    // Find concerts by year
    List<Concert> findByYear(int year);


    // Find concerts by artist name
    @Query("""
           SELECT c 
           FROM Concert c
           WHERE LOWER(c.artist.name) LIKE LOWER(CONCAT('%', :name, '%'))
           """)
    List<Concert> findByArtistName(@Param("name") String name);


    // Find concerts by venue name
    @Query("""
           SELECT c
           FROM Concert c
           WHERE LOWER(c.venue.name) LIKE LOWER(CONCAT('%', :name, '%'))
           """)
    List<Concert> findByVenueName(@Param("name") String name);


    // Find concerts by venue city
    @Query("""
           SELECT c
           FROM Concert c
           WHERE LOWER(c.venue.city) LIKE LOWER(CONCAT('%', :city, '%'))
           """)
    List<Concert> findByCity(@Param("city") String city);


    // Find concerts under a maximum ticket price
    @Query("""
           SELECT c
           FROM Concert c
           WHERE c.ticketPrice <= :price
           """)
    List<Concert> findByMaximumPrice(@Param("price") double price);


    // Find concerts between two ticket prices
    @Query("""
           SELECT c
           FROM Concert c
           WHERE c.price BETWEEN :min AND :max
           """)
    List<Concert> findByPriceRange(
            @Param("min") double min,
            @Param("max") double max
    );


    // Find concerts by price and year
    @Query("""
       SELECT c 
       FROM Concert c 
       WHERE c.ticketPrice = :price 
       AND c.concertYear = :year
       """)
    List<Concert> findByPriceAndYear(
            @Param("price") double price,
            @Param("year") int year
    );
}