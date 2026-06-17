package com.pluralsight.concerttracker.repositories;

import com.pluralsight.concerttracker.models.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoterRepository extends JpaRepository<Promoter, Long> {
}
