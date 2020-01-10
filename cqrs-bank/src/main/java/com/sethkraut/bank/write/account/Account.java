package com.sethkraut.bank.write.account;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
    @AggregateIdentifier
    private String id;
    private BigDecimal amount;

    @CommandHandler
    public Account(OpenAccount command) {
        AggregateLifecycle.apply(new AccountOpened(command.getId(), command.getAmount()));
    }

    @CommandHandler
    public void handle(CreditAccount command) {
        AggregateLifecycle.apply(new AccountCredited(id, command.getAmount()));
    }

    @CommandHandler
    public void handle(DebitAccount command) {
        if (hasAtLeast(command.getAmount())) {
            throw new IllegalArgumentException("Not enough money");
        }
        AggregateLifecycle.apply(new AccountDebited(id, command.getAmount()));
    }

    @EventHandler
    public void on(AccountOpened event) {
        this.id = event.getAccountId();
        this.amount = event.getAmount();
    }

    @EventHandler
    public void on(AccountCredited event) {
        this.amount = amount.add(event.getAmount());
    }

    @EventHandler
    public void on(AccountDebited event) {
        this.amount = amount.subtract(event.getAmount());
    }


    private boolean hasAtLeast(BigDecimal amount) {
        return amount.compareTo(this.amount) < 0;
    }
}
