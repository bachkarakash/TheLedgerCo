package com.ledger.model;

import com.ledger.data.DataStore;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTest {

    DataStore dataStore = DataStore.getInstance();
    LoanTest() {
        inilializeLoanDetails();
    }

    @Test
    public void testEmiAmount() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = dataStore.getLoanRecord(key);
        assertEquals(loan.emiAmount(), 200);
    }

    @Test
    public void testRepaymentAmount() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = dataStore.getLoanRecord(key);
        assertEquals(loan.totalRepaymentAmount(), 12000);
    }

    @Test
    public void testLumpSumAmount() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = dataStore.getLoanRecord(key);
        assertEquals(loan.lumpSumPaidTillEmiNo(5), 0);
    }

    public void inilializeLoanDetails() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 10000, 5, 4, null);
        dataStore.saveLoanDetails(loan);

        borrowerName = "TestBorrowerName2";
        key = new Pair<>(bankName, borrowerName);
        loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 2000, 2, 2, null);
        dataStore.saveLoanDetails(loan);
    }

    public void updateLoanDetails(Loan loan, String bankName, String borrowerName, double principalAmount,
                                  int tenure, double rateOfInterest, List<Payment> payments) {
        loan.setBankName(bankName);
        loan.setBorrowerName(borrowerName);
        loan.setPrincipalAmount(principalAmount);
        loan.setTenure(tenure);
        loan.setRateOfInterest(rateOfInterest);
        loan.setPayments(payments);
    }
}
