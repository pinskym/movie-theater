package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), 1250, true),
                1,
                LocalDateTime.now()
        );
        assertEquals(2850, new Reservation(customer, showing, 3).totalFee());
    }
}
