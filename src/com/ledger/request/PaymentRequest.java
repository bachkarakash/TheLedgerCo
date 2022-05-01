package com.ledger.request;

public class PaymentRequest {
    String bankName, borrowerName;
    double lumpSumAmount;
    int emiNo;

    public PaymentRequest(String bankName, String borrowerName, double lumpSumAmount, int emiNo) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumpSumAmount = lumpSumAmount;
        this.emiNo = emiNo;
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

    public double getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(double lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public int getEmiNo() {
        return emiNo;
    }

    public void setEmiNo(int emiNo) {
        this.emiNo = emiNo;
    }
}
