package com.jpmc.theater;

public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int ticketsNumber;

    public Customer getCustomer() {
        return customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public Reservation(Customer customer, Showing showing, int ticketsNumber) {
        this.customer = customer;
        this.showing = showing;
        this.ticketsNumber = ticketsNumber;
    }

    public long totalFee() {
        return showing.getDiscountedPrice(ticketsNumber);
    }
}