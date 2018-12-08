package com.FutureGadgetLabs.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket implements Serializable {
    private int ticketId;
    private int lotId;
    private Timestamp timeIn;
    private Timestamp timeOut;
    private double price;
    private boolean lost;

    public Ticket() {}

    public Ticket(int ticketId, int lotId, Timestamp timeIn, Timestamp timeOut, double price, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = price;
        this.lost = lost;
    }

    /**
     * Constructor for a ticket without a price.
     *
     * @param ticketId Integer: unique identifier for parking ticket db object.
     * @param lotId Integer: unique identifier for parking lot db object.
     * @param timeIn Timestamp: timestamp value for ticket creation time without timezone.
     * @param timeOut Timestamp: timestamp value for ticket resolution time without timezone.
     * @param lost Boolean: flag for a ticket that was lost or exceeded maximum pricing duration.
     *
     */
    public Ticket(int ticketId, int lotId, Timestamp timeIn, Timestamp timeOut, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = 0;
        this.lost = lost;
    }

    /**
     * Constructor for a ticket without an exit time or price.
     *
     * @param ticketId Integer: unique identifier for parking ticket db object.
     * @param lotId Integer: unique identifier for parking lot db object.
     * @param timeIn Timestamp: timestamp value for ticket creation time without timezone.
     * @param lost Boolean: flag for a ticket that was lost or exceeded maximum pricing duration.
     *
     */
    public Ticket(int ticketId, int lotId, Timestamp timeIn, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.timeIn = timeIn;
        this.timeOut = null;
        this.price = 0;
        this.lost = lost;
    }

    public int getTicketId() { return ticketId; }

    public int getLotId() {
        return lotId;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public double getPrice() {
        return price;
    }

    public boolean getLost() { return lost; }

    public void setPrice(double price) {this.price=price;}

    public void setLost(boolean lost) { this.lost = lost;}
}

