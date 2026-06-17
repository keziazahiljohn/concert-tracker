package com.pluralsight.concerttracker.models;
import jakarta.persistence.*;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;

    public Artist() {

    }

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
}
