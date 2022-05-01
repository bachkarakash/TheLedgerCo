package com.ledger.factory;

import com.ledger.handler.BalanceHandler;
import com.ledger.handler.LoanHandler;
import com.ledger.handler.PaymentHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RequestHandlerFactoryTest {

    @Test
    public void testGetRequestHandlerForLoan() {
        String command = "LOAN IDIDI Dale 5000 1 6";
        assertEquals(RequestHandlerFactory.getRequestHandler(command) instanceof LoanHandler, true);
    }

    @Test
    public void testGetRequestHandlerForBalance() {
        String command = "BALANCE IDIDI Dale 3";
        assertEquals(RequestHandlerFactory.getRequestHandler(command) instanceof BalanceHandler, true);
    }

    @Test
    public void testGetRequestHandlerForPayment() {
        String command = "PAYMENT UON Shelly 7000 12";
        assertEquals(RequestHandlerFactory.getRequestHandler(command) instanceof PaymentHandler, true);
    }

    @Test
    public void testGetRequestHandlerForInvalidRequest() {
        String command = "RANDOM UON Shelly 7000 12";
        assertEquals(RequestHandlerFactory.getRequestHandler(command), null);
        assertFalse(RequestHandlerFactory.getRequestHandler(command) instanceof LoanHandler);
        assertFalse(RequestHandlerFactory.getRequestHandler(command) instanceof BalanceHandler);
        assertFalse(RequestHandlerFactory.getRequestHandler(command) instanceof PaymentHandler);
    }

}
