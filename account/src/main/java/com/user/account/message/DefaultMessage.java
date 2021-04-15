package com.user.account.message;

public class DefaultMessage {
    public final static String DEBIT = "Debit";
    public final static String CREDIT = "Credit";
    public final static String TRANSACTION_SUCCESS = "Transaction Successful";
    public final static String TRANSACTION_FAILED_LOW_BALANCE = "Transaction Failed due to Low Account Balance";
    public final static String TRANSACTION_FAILED_INVALID_AMOUNT = "Transaction Failed due to Invalid Amount";
    public final static String VALIDATION_FAILED = "Invalid Input";
    public final static String INVALID_ACCOUNT_NUMBER = "Invalid Account Number or No transaction available";
    public final static String INVALID_TRANSACTION_USERS = "Credit account number is same as debit account number";
    public final static String INVALID_FUND_TRANSFER_DETAILS ="Invalid user account number or transaction amount";
    public final static String JSON_PARSE_EXCEPTION = "Account number and transaction amount must be integer";
}
