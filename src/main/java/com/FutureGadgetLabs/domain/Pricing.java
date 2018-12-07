package com.FutureGadgetLabs.domain;

import java.io.Serializable;
import java.util.Comparator;

public class Pricing implements Serializable {

    private int pricingId;
    private int pricingSchemeNumber;
    private int duration;
    private String granularity;
    private double price;

    public Pricing() {}

    public Pricing(int pricingSchemeNumber, int duration, String granularity, double price) {
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.duration = duration;
        this.granularity = granularity;
        this.price = price;
    }

    public Pricing(int pricingId, int pricingSchemeNumber, int duration, String granularity, double price) {
        this.pricingId = pricingId;
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.duration = duration;
        this.granularity = granularity;
        this.price = price;
    }

    public int getPricingId() {
        return pricingId;
    }

    public int getPricingSchemeNumber() {
        return pricingSchemeNumber;
    }

    public int getDuration() {
        return duration;
    }

    public String getGranularity() {
        return granularity;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Comparator for sorting durations in ascending order.
     */
    public static Comparator<Pricing> sortByDuration = (o1, o2) -> (Integer.compare(o1.duration, o2.duration));

}
