package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.request.PaymentRequest;
import com.ledger.helper.RequestToModelTranslator;
import com.ledger.response.DefaultResponse;

import java.util.NoSuchElementException;

public class PaymentHandler implements RequestHandler{

    private PaymentRequest paymentRequest;
    private DataStore dataStore;

    public PaymentHandler(PaymentRequest paymentRequest, DataStore dataStore) {
        this.paymentRequest = paymentRequest;
        this.dataStore = dataStore;
    }

    @Override
    public DefaultResponse handleRequest() {
        Loan loan = dataStore.getLoanDetails(paymentRequest.getBankName(), paymentRequest.getBorrowerName());
        if(loan == null)
            throw new NoSuchElementException();
        int totalEmis = loan.getTenure() * 12;
        if(paymentRequest.getEmiNo() > totalEmis)
            throw new IllegalArgumentException();
        Payment payment = RequestToModelTranslator.toPaymentModel(paymentRequest);
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setSuccess(dataStore.savePaymentDetails(paymentRequest.getBankName(), paymentRequest.getBorrowerName(), payment));
        return defaultResponse;
    }
}
