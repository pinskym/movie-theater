package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeeTests {
    private final int qty = 3;
    private final long price = 1500;
    @Test
    void noDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
        assertEquals(price, showing.getDiscountedPrice());
    }

    @Test
    void noDiscountMany() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
        assertEquals(price, qty, showing.getDiscountedPrice(qty));
    }

    @Test
    void specialDiscount_20P() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price, true);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
        assertEquals(price - (price * 20) / 100, showing.getDiscountedPrice());
    }

    @Test
    void discountMany() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price, true);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
        assertEquals(qty * (price - ( price * 20) / 100), showing.getDiscountedPrice(qty));
    }

    @Test
    void firstRunDiscount_3D() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
        assertEquals(price - 300, showing.getDiscountedPrice());
    }

    @Test
    void secondRunDiscount_2D() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
        assertEquals(price - 200, showing.getDiscountedPrice());
    }

    @Test
    void seventhDiscount_1D() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 4, LocalDateTime.of(LocalDate.of(2023, Month.APRIL, 7), LocalTime.now()));
        assertEquals(price - 100, showing.getDiscountedPrice());
    }

    @Test
    void lunchTimeDiscount_25P() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price);
        Showing showing = new Showing(spiderMan, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0, 0)));
        assertEquals(price - ( price * 25) / 100, showing.getDiscountedPrice());
    }

    @Test
    void bestDiscount_25P() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), price, true);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2023, Month.APRIL, 7), LocalTime.of(12, 0, 0)));
        assertEquals(price - ( price * 25) / 100, showing.getDiscountedPrice());
    }
}
