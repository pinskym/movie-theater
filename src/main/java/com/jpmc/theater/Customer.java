package com.jpmc.theater;

import java.util.Objects;

public class Customer {
    private final String name;
    private final String id;

    /**
     * @param name customer name
     * @param id   customer id
     */
    public Customer(String name, String id) {
        this.id = id; // NOTE - id is not used anywhere at the moment
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "name: " + name + " (" + id + ")";
    }
}