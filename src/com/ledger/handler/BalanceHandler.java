package com.ledger.handler;

import com.ledger.data.DataStore;
import com.ledger.model.Loan;
import com.ledger.request.BalanceRequest;
import com.ledger.response.BalanceResponse;
import com.ledger.response.DefaultResponse;

import java.util.NoSuchElementException;

public class BalanceHandler implements RequestHandler{

    private BalanceRequest balanceRequest;

    private DataStore dataStore;

    public BalanceHandler(BalanceRequest balanceRequest, DataStore dataStore) {
        this.balanceRequest = balanceRequest;
        this.dataStore = dataStore;
    }

    @Override
    public DefaultResponse handleRequest() {
        Loan loan = dataStore.getLoanDetails(balanceRequest.getBankName(), balanceRequest.getBorrowerName());
        if(loan == null)
            throw new NoSuchElementException();
        double emiAmount = loan.emiAmount();
        double totalAmountWithEmi = balanceRequest.getEmiNo() * emiAmount;
        double totalLumpSumPaid = loan.lumpSumPaidTillEmiNo(balanceRequest.getEmiNo());
        double totalAmountPaidTillNow = totalAmountWithEmi + totalLumpSumPaid;
        double totalAmountPending = loan.totalRepaymentAmount() - totalAmountPaidTillNow;
        int remainingEmis = (int) Math.ceil(totalAmountPending/emiAmount);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setBankName(balanceRequest.getBankName());
        balanceResponse.setBorrowerName(balanceRequest.getBorrowerName());
        balanceResponse.setAmountPaid(totalAmountPaidTillNow);
        balanceResponse.setRemainingEmis(remainingEmis);
        balanceResponse.setSuccess(true);
        return balanceResponse;
    }
}
