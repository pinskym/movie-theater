package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing {
    private final Movie movie;
    private final int orderOfShow;
    private final LocalDateTime showStartTime;

    public Showing(Movie movie, int orderOfShow, LocalDateTime showStartTime) {
        this.movie = movie;
        this.orderOfShow = orderOfShow;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public long getFullPrice() {
        return movie.getTicketPrice();
    }

    public int getOrderOfShow() {
        return orderOfShow;
    }

    public long getDiscountedPrice() {
        return getDiscountedPrice(1);
    }

    public long getDiscountedPrice(int ticketsNumber) {
        return (movie.getTicketPrice() - getDiscount(movie.getTicketPrice()))  * ticketsNumber;
    }

    private long getDiscount(long ticketPrice) {
        long bestDiscount = 0;
        if (movie.isSpecial()) {
            bestDiscount = Long.max(bestDiscount, (ticketPrice * 20) / 100);  // 20% discount for special movie
        }
        if (orderOfShow == 1) {
            bestDiscount = Long.max(bestDiscount, 300);  // $3 discount for 1st show
        }
        if (orderOfShow == 2) {
            bestDiscount = Long.max(bestDiscount, 200); // $2 discount for 2nd show
        }
        if (getStartTime().isAfter(LocalDateTime.of(getStartTime().toLocalDate(), LocalTime.of(10,59)))
         && getStartTime().isBefore(LocalDateTime.of(getStartTime().toLocalDate(), LocalTime.of(16,1)))) {
            bestDiscount = Long.max(bestDiscount, (ticketPrice * 25) / 100);  // 25% discount for between 11 AM and 4 PM
        }
        if (getStartTime().toLocalDate().getDayOfMonth() == 7) {
            bestDiscount = Long.max(bestDiscount, 100);  // $1 discount for lucky day 7
        }
        return bestDiscount;
    }}
