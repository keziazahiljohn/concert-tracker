package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter, Integer> {

    // Find promoters by name
    List<Promoter> findByNameContainingIgnoreCase(String name);

}