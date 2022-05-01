package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.model.Loan;
import com.ledger.request.LoanRequest;
import com.ledger.helper.RequestToModelTranslator;
import com.ledger.response.DefaultResponse;

public class LoanHandler implements RequestHandler{

    private LoanRequest loanRequest;
    private DataStore dataStore;

    public LoanHandler(LoanRequest loanRequest, DataStore dataStore) {
        this.loanRequest = loanRequest;
        this.dataStore = dataStore;
    }

    @Override
    public DefaultResponse handleRequest() {
        if(dataStore.getLoanDetails(loanRequest.getBankName(), loanRequest.getBorrowerName()) != null)
            throw new IllegalArgumentException();
        Loan loan = RequestToModelTranslator.toLoanModel(loanRequest);
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setSuccess(dataStore.saveLoanDetails(loan));
        return defaultResponse;
    }
}
