package com.ledger.request;

public class LoanRequest {
    String bankName, borrowerName;
    double principalAmount, rateOfInterest;
    int tenure;

    public LoanRequest(String bankName, String borrowerName, double principalAmount, double rateOfInterest, int tenure) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.rateOfInterest = rateOfInterest;
        this.tenure = tenure;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }
}
