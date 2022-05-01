package com.ledger.data;

import com.ledger.model.Loan;
import com.ledger.model.Payment;
import javafx.util.Pair;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.Dictionary;

public class DataStore {

    private static DataStore dataStore = new DataStore();
    private Dictionary<Pair<String, String>, Loan> laonsRecords = new Cache();

    private DataStore() {

    }

    public Dictionary<Pair<String, String>, Loan> getLoansRecords() {
        return laonsRecords;
    }

    public static DataStore getInstance() {
        return dataStore;
    }
    public Loan getLoanDetails(String bankName, String borrowerName) {
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        return getLoanRecord(key);
    }

    public boolean saveLoanDetails(Loan loan) {
        Pair<String, String> key = new Pair<>(loan.getBankName(), loan.getBorrowerName());
        if(getLoanDetails(loan.getBankName(), loan.getBorrowerName()) != null) return false;
        laonsRecords.put(key, loan);
        return true;
    }

    public boolean savePaymentDetails(String bankName, String borrowerName, Payment payment) {
        Pair<String, String> key = new Pair<>(bankName, borrowerName);
        Loan loan = getLoanRecord(key);
        if(loan == null) return false;
        if(loan.getPayments() == null) loan.setPayments(new ArrayList<>());
        loan.getPayments().add(payment);
        return true;
    }

    public Loan getLoanRecord(Pair<String, String> key) {
        return laonsRecords.get(key);
    }

    public void removeLoanRecord(Pair<String, String> key) {
        Loan loan = getLoanRecord(key);
        if(loan != null) laonsRecords.remove(key);
    }
}
