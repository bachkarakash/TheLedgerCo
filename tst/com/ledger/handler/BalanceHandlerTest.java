package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.request.BalanceRequest;
import com.ledger.response.BalanceResponse;
import com.ledger.response.DefaultResponse;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceHandlerTest {

    DataStore dataStore = DataStore.getInstance();


    @Test
    public void testBalanceRequest() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        inilializeLoanDetails();
        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, 5);
        BalanceHandler balanceHandler = new BalanceHandler(balanceRequest, dataStore);
        BalanceResponse balanceResponse = (BalanceResponse)balanceHandler.handleRequest();
        assertEquals(balanceResponse.getAmountPaid(), 1000);
        assertEquals(balanceResponse.getRemainingEmis(), 55);
    }

    @Test
    public void testBalanceRequest2() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        inilializeLoanDetails();
        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, 40);
        BalanceHandler balanceHandler = new BalanceHandler(balanceRequest, dataStore);
        BalanceResponse balanceResponse = (BalanceResponse)balanceHandler.handleRequest();
        assertEquals(balanceResponse.getAmountPaid(), 8000);
        assertEquals(balanceResponse.getRemainingEmis(), 20);
    }

    @Test
    public void testBalanceRequest3() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName2";
        inilializeLoanDetails();
        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, 12);
        BalanceHandler balanceHandler = new BalanceHandler(balanceRequest, dataStore);
        BalanceResponse balanceResponse = (BalanceResponse)balanceHandler.handleRequest();
        assertEquals(balanceResponse.getAmountPaid(), 1044);
        assertEquals(balanceResponse.getRemainingEmis(), 12);
    }

    @Test
    public void testBalanceRequest4() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName2";
        inilializeLoanDetails();
        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, 0);
        BalanceHandler balanceHandler = new BalanceHandler(balanceRequest, dataStore);
        BalanceResponse balanceResponse = (BalanceResponse)balanceHandler.handleRequest();
        assertEquals(balanceResponse.getAmountPaid(), 0);
        assertEquals(balanceResponse.getRemainingEmis(), 24);
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
