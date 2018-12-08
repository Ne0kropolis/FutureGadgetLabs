package com.FutureGadgetLabs.domain;

/**
 * Pricing Scheme maintains a list of several pricing objects with a shared pricing scheme number.
 */

import java.util.List;

public class PricingScheme {
    private List<Pricing> prices;

    private double lostTicketPrice;

    public PricingScheme(List<Pricing> prices) {
        this.prices = prices;
    }

    public double getLostTicketPrice() {
        return lostTicketPrice;
    }

    public List<Pricing> getPrices() {return prices;}

    /**
     * Iterates through the list of Pricing objects in the current PricingScheme and extracts the Lost ticket price and
     * multiplies the listed durations by their granular values to a duration in minutes.
     *
     * @return sorted list of Pricing objects by their durations.
     */
    public List<Pricing> getDurations() {

        for (int i=0; i<prices.size(); i++) {
            switch (prices.get(i).getGranularity().toUpperCase()) {
                case"L":
                    this.setLostTicketPrice(prices.get(i).getPrice());
                    break;
                case"S":
                    prices.get(i).setDuration(prices.get(i).getDuration()/60);
                    break;
                case"M":
                    prices.get(i).setDuration(prices.get(i).getDuration());
                    break;
                case"H":
                    prices.get(i).setDuration(prices.get(i).getDuration()*60);
                    break;
                case"D":
                    prices.get(i).setDuration(prices.get(i).getDuration()*60*24);
                    break;
            }
        }
        prices.sort(Pricing.sortByDuration);
        return  prices;
    }

    /**
     * Sets the lost price for the given pricing scheme
     * @param lostTicketPrice: lost ticket price obtained from getDurations()
     */
    public void setLostTicketPrice(double lostTicketPrice) {
        this.lostTicketPrice = lostTicketPrice;
    }

}

