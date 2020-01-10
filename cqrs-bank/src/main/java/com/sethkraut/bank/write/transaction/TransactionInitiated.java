package com.sethkraut.bank.write.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TransactionInitiated {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
}
