package com.sethkraut.bank.write.transaction;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {
    public enum Status {
        INITIATED,
        COMPLETED,
        REJECTED
    }

    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
    private Status status;

    @CommandHandler
    public Transaction(TransferMoney transfer) {
        AggregateLifecycle.apply(new TransactionInitiated(
                transfer.getTransactionId(),
                transfer.getFromAccountId(),
                transfer.getToAccountId(),
                transfer.getAmount()));
    }

    @EventHandler
    public void on(TransactionInitiated event) {
        this.transactionId = event.getTransactionId();
        this.fromAccountId = event.getFromAccountId();
        this.toAccountId = event.getToAccountId();
        this.amount = event.getAmount();
        this.status = Status.INITIATED;
    }

    @EventHandler
    public void on(TransactionCompleted event) {
        this.status = Status.COMPLETED;
    }

    @EventHandler
    public void on(TransactionRejected event) {
        this.status = Status.REJECTED;
    }
}
