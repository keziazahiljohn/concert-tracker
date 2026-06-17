package com.pluralsight.concerttracker.models;
import jakarta.persistence.*;

@Entity
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int concert_year;
    private double ticket_price;
    private int tickets_sold;

    @ManyToOne(optional = false)
    private Artist artist;

    @ManyToOne(optional = false)
    private Venue venue;

    @ManyToOne(optional = false)
    private Promoter promoter;

    public Concert(){}

    public Concert(int concert_year, double ticket_price, int tickets_sold, Artist artist, Venue venue, Promoter promoter) {
        this.concert_year = concert_year;
        this.ticket_price = ticket_price;
        this.tickets_sold = tickets_sold;
        this.artist = artist;
        this.venue = venue;
        this.promoter = promoter;
    }
}
