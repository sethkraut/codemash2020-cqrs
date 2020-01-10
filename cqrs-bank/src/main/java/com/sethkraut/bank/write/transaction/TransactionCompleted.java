package com.sethkraut.bank.write.transaction;

import java.math.BigDecimal;

public class TransactionCompleted {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
}
