package com.pluralsight.concerttracker.models;
import jakarta.persistence.*;

@Entity
public class Promoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Promoter(){}

    public Promoter(String name) {
        this.name = name;
    }
}
