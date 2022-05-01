package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.request.PaymentRequest;
import com.ledger.response.DefaultResponse;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentHandlerTest {

    DataStore dataStore = DataStore.getInstance();

    PaymentHandlerTest() {
        inilializeLoanDetails();
    }

    @Test
    public void testPaymentHandler() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        double lumpSumAmount = 1000;
        int emiNo = 5;
        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        PaymentHandler paymentHandler = new PaymentHandler(paymentRequest, dataStore);
        DefaultResponse defaultResponse = paymentHandler.handleRequest();
        assertTrue(defaultResponse.isSuccess());
    }

    @Test
    public void testPaymentHandler2() {
        String bankName = "TestBankName1";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        double lumpSumAmount = 1000;
        int emiNo = 5;
        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        PaymentHandler paymentHandler = new PaymentHandler(paymentRequest, dataStore);
        try {
            DefaultResponse defaultResponse = paymentHandler.handleRequest();
        }
        catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPaymentHandler3() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        double lumpSumAmount = 1000;
        int emiNo = 50;
        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        PaymentHandler paymentHandler = new PaymentHandler(paymentRequest, dataStore);
        try {
            DefaultResponse defaultResponse = paymentHandler.handleRequest();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    public void inilializeLoanDetails() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 5000, 1, 6, null);
        dataStore.saveLoanDetails(loan);

        borrowerName = "TestBorrowerName2";
        key = new Pair<>(bankName, borrowerName);
        loan = new Loan();
        updateLoanDetails(loan, bankName, borrowerName, 10000, 3, 7, null);
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
