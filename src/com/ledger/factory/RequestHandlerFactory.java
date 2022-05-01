package com.ledger.factory;

import com.ledger.data.DataStore;
import com.ledger.handler.BalanceHandler;
import com.ledger.handler.LoanHandler;
import com.ledger.handler.PaymentHandler;
import com.ledger.handler.RequestHandler;
import com.ledger.helper.LedgerConstants;
import com.ledger.request.BalanceRequest;
import com.ledger.request.LoanRequest;
import com.ledger.request.PaymentRequest;

public class RequestHandlerFactory {

    public static RequestHandler getRequestHandler(String command) {
        RequestHandler requestHandler = null;
        DataStore dataStore = DataStore.getInstance();
        String[] params = command.split(" ");
        if(params.length > 0) {
            char c = params[0].charAt(0);
            params[0] = c != 'L' && c != 'P' && c != 'B' ? params[0].substring(1) : params[0];
            requestHandler = params[0].equalsIgnoreCase(LedgerConstants.Balance) ? getBalanceHandler(params, dataStore) :
                    params[0].equalsIgnoreCase(LedgerConstants.Loan) ? getLoanHandler(params, dataStore) :
                            params[0].equalsIgnoreCase(LedgerConstants.Payment) ? getPaymentHandler(params, dataStore) : null;
        }
        return requestHandler;
    }

    public static BalanceHandler getBalanceHandler(String[] psrams, DataStore dataStore) {
        String bankName = psrams[1];
        String borrowerName = psrams[2];
        int emiNo = Integer.parseInt(psrams[3]);
        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, emiNo);
        return new BalanceHandler(balanceRequest, dataStore);
    }

    public static LoanHandler getLoanHandler(String[] params, DataStore dataStore) {
        String bankName = params[1];
        String borrowerName = params[2];
        double principalAmount = Double.parseDouble(params[3]);
        int tenure = Integer.parseInt(params[4]);
        double rateOfInterest = Double.parseDouble(params[5]);
        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, rateOfInterest, tenure);
        return new LoanHandler(loanRequest, dataStore);
    }

    public static PaymentHandler getPaymentHandler(String[] params, DataStore dataStore) {
        String bankName = params[1];
        String borrowerName = params[2];
        double lumpSumAmount = Double.parseDouble(params[3]);
        int emiNo = Integer.parseInt(params[4]);
        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        return new PaymentHandler(paymentRequest, dataStore);
    }
}
