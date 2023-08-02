package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private final String title;
    private final String description;
    private final Duration runningTime;
    private final long ticketPriceCents;
    private final boolean special;

    public Movie(String title, String description, Duration runningTime, long ticketPriceCents) {
        this(title, description, runningTime, ticketPriceCents, false);
    }

    public Movie(String title, String description, Duration runningTime, long ticketPriceCents, boolean special) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPriceCents = ticketPriceCents;
        this.special = special;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public String getDescription() {
        return description;
    }

    public long getTicketPrice() {
        return ticketPriceCents;
    }

    public boolean isSpecial() {
        return special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return ticketPriceCents == movie.ticketPriceCents && special == movie.special && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(runningTime, movie.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPriceCents, special);
    }
}