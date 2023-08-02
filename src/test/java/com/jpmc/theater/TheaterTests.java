package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TheaterTests {
    private final Customer john = new Customer("John Doe", "id-12345");

    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDate.now());
        Reservation reservation = theater.reserve("Turning Red",  LocalTime.of(19, 30), john,4);
        assertEquals(4400, reservation.totalFee());
    }

    @Test
    void unknownMovie() {
        Theater theater = new Theater(LocalDate.now());
        Reservation reservation = theater.reserve("Pulp Fiction",  LocalTime.of(19, 30), john,4);
        assertNull(reservation);
    }


    @Test
    void wrongTime() {
        Theater theater = new Theater(LocalDate.now());
        Reservation reservation = theater.reserve("Turning Red",  LocalTime.of(18, 30), john,4);
        assertNull(reservation);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDate.now());
        theater.printSchedule();
    }
}
