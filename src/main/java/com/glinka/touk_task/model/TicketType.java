package com.glinka.touk_task.model;

import javax.persistence.Entity;

public enum TicketType {
    ADULT(25),
    STUDENT(18),
    CHILD(12.5);

    private double price;

    TicketType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
