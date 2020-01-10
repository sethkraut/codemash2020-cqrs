package com.sethkraut.bank.write.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CreditAccount {
    @RoutingKey
    private String accountId;
    private BigDecimal amount;
}
