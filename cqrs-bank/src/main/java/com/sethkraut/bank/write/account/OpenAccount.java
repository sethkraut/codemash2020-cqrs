package com.sethkraut.bank.write.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OpenAccount {
    @RoutingKey
    private String id;
    private BigDecimal amount;
}
