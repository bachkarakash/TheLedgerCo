package com.ledger.helper;

import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.request.LoanRequest;
import com.ledger.request.PaymentRequest;

public class RequestToModelTranslator {

    public static Loan toLoanModel(LoanRequest loanRequest) {
        if(loanRequest == null) return null;
        Loan loan = new Loan();
        loan.setBankName(loanRequest.getBankName());
        loan.setBorrowerName(loanRequest.getBorrowerName());
        loan.setPrincipalAmount(loanRequest.getPrincipalAmount());
        loan.setTenure(loanRequest.getTenure());
        loan.setRateOfInterest(loanRequest.getRateOfInterest());
        return loan;
    }

    public static Payment toPaymentModel(PaymentRequest paymentRequest) {
        if (paymentRequest == null) return null;
        Payment payment = new Payment();
        payment.setEmiNo(paymentRequest.getEmiNo());
        payment.setAmount(paymentRequest.getLumpSumAmount());
        return payment;
    }
}
