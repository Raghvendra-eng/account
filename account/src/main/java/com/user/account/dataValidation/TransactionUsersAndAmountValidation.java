package com.user.account.dataValidation;

import com.user.account.entity.FundTransferDetails;

public class TransactionUsersAndAmountValidation {
    public static boolean transactionUsersAndAmountValidation(FundTransferDetails fundTransferDetails){
        if(fundTransferDetails.getDebitUserAccountNumber().compareTo(1L) <= 0)
            return false;
        if(fundTransferDetails.getCreditUserAccountNumber().compareTo(1L) <= 0)
            return false;
        return fundTransferDetails.getTransactionAmount().compareTo(1L) > 0;
    }
}
