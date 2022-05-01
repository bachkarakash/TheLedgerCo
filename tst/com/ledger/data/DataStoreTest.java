package com.ledger.data;

import com.ledger.model.Loan;
import com.ledger.model.Payment;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DataStoreTest {

    DataStore dataStore = DataStore.getInstance();
    @Test
    public void testNoLoans() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        assertEquals(dataStore.getLoanRecord(key), null);
    }

    @Test
    public void testSaveLoans() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 10000, 5, 4, null);
        dataStore.saveLoanDetails(loan);
        assertEquals(dataStore.getLoanRecord(key).getBankName(), bankName);
        assertEquals(dataStore.getLoanRecord(key).getBorrowerName(), borrowerName);
        dataStore.removeLoanRecord(key);
    }

    @Test
    public void testSavePaymentDetails() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 10000, 5, 4, null);
        dataStore.saveLoanDetails(loan);
        Payment payment = new Payment();
        payment.setEmiNo(5);
        payment.setAmount(1000);
        dataStore.savePaymentDetails(bankName, borrowerName, payment);
        assertEquals(dataStore.getLoanRecord(key).getPayments().size(), 1);
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
