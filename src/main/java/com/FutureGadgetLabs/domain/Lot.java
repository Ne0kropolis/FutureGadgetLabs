package com.FutureGadgetLabs.domain;

import java.io.Serializable;

public class Lot implements Serializable {
    private int lotId;
    private int pricingSchemeNumber;
    private String lotName;
    private String lotAddress;
    private int lotCapacity;

    public Lot() { }

    public Lot(int pricingSchemeNumber, String lotName, String lotAddress, int lotCapacity) {
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.lotName = lotName;
        this.lotAddress = lotAddress;
        this.lotCapacity = lotCapacity;
    }

    public Lot(int lotId, int pricingSchemeNumber, String lotName, String lotAddress, int lotCapacity) {
        this.lotId = lotId;
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.lotName = lotName;
        this.lotAddress = lotAddress;
        this.lotCapacity = lotCapacity;
    }

    public int getLotId() {
        return lotId;
    }

    public int getPricingSchemeNumber() {
        return pricingSchemeNumber;
    }

    public String getLotName() {
        return lotName;
    }

    public String getLotAddress() {
        return lotAddress;
    }

    public int getlotCapacity() {
        return lotCapacity;
    }
}
