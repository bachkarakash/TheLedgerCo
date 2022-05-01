package com.ledger.helper;

import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.request.LoanRequest;
import com.ledger.request.PaymentRequest;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RequestToModelTranslatorTest {

    @Test
    public void testLoanModelTranslator() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        double principalAmount = 10000;
        int tenure = 5;
        double rateOfInterest = 4;
        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, rateOfInterest, tenure);
        Loan loan = RequestToModelTranslator.toLoanModel(loanRequest);
        assertFalse(loan == null);
        assertEquals(loan.getBorrowerName(), borrowerName);
    }

    @Test
    public void testPaymentModelTranslator() {
        String bankName = "TestBankName";
        String borrowerName = "TestBorrowerName";
        double lumpSumAmount = 1000;
        int emiNo = 5;
        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        Payment payment = RequestToModelTranslator.toPaymentModel(paymentRequest);
        assertFalse(payment == null);
        assertEquals(payment.getAmount(), 1000);
    }
}
