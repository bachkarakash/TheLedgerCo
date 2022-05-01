package com.ledger.response;

import java.io.Serializable;

public class BalanceResponse extends DefaultResponse implements Serializable {
    String bankName, borrowerName;
    double amountPaid;
    int remainingEmis;

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

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getRemainingEmis() {
        return remainingEmis;
    }

    public void setRemainingEmis(int remainingEmis) {
        this.remainingEmis = remainingEmis;
    }

    @Override
    public String toString() {
        return bankName + " " + borrowerName + " " + (int) Math.ceil(amountPaid) + " " + remainingEmis + "\n";
    }
}
