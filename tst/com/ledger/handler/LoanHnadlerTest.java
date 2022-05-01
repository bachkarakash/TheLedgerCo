package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.request.LoanRequest;
import com.ledger.response.DefaultResponse;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoanHnadlerTest {

    DataStore dataStore = DataStore.getInstance();

    @Test
    public void testLoanHandler() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        double principalAmount = 10000;
        int tenure = 5;
        double rateOfInterest = 4;
        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, rateOfInterest, tenure);
        LoanHandler loanHandler = new LoanHandler(loanRequest, dataStore);
        DefaultResponse defaultResponse = loanHandler.handleRequest();
        assertTrue(defaultResponse.isSuccess());
        assertFalse(dataStore.getLoanRecord(key).equals(null));
        dataStore.removeLoanRecord(key);
    }

    @Test
    public void testLoanHandler2() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        double principalAmount = 10000;
        int tenure = 5;
        double rateOfInterest = 4;
        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, rateOfInterest, tenure);
        LoanHandler loanHandler = new LoanHandler(loanRequest, dataStore);
        DefaultResponse defaultResponse = loanHandler.handleRequest();
        try {
            defaultResponse = loanHandler.handleRequest();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        assertFalse(dataStore.getLoanRecord(key) == null);
    }
}
