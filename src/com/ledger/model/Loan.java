package com.ledger.model;

import java.util.List;

public class Loan {

    String bankName, borrowerName;
    double principalAmount, rateOfInterest;
    int tenure;
    List<Payment> payments;


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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public double emiAmount() {
        return Math.ceil(totalRepaymentAmount() / 12 / tenure);
    }

    public double lumpSumPaidTillEmiNo(int emiNo) {
        if(payments != null && payments.size() > 0) {
            return payments.stream().filter(x -> x.emiNo <= emiNo).mapToDouble(y-> y.getAmount()).sum();
        }
        return 0;
    }

    public double totalRepaymentAmount() {
        if(tenure > 0)
            return principalAmount + (principalAmount * tenure * rateOfInterest)/100;
        else return 0;
    }
}
