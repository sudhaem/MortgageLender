package com.mortgage.lender;

public class Lender {
    private double availableFunds;

    public Lender(double availableFunds) {
        this.availableFunds = availableFunds;
    }
    public double getAvailableFunds() {
        return this.availableFunds;
    }

    public void depositFunds(double amount) {
        this.availableFunds+= amount;
    }
}
