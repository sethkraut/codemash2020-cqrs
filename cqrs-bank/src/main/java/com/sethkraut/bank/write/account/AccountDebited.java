package com.sethkraut.bank.write.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class AccountDebited {
    @AggregateIdentifier
    private String accountId;
    private BigDecimal amount;
}
