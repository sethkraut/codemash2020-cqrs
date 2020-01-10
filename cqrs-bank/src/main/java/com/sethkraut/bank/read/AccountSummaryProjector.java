package com.sethkraut.bank.read;

import com.sethkraut.bank.write.account.AccountCredited;
import com.sethkraut.bank.write.account.AccountDebited;
import com.sethkraut.bank.write.account.AccountOpened;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountSummaryProjector {
    private AccountSummaryRepository repository;

    @EventHandler
    public void on(AccountOpened event) {
        repository.save(new AccountSummaryView(event.getAccountId(), event.getAmount()));
    }

    @EventHandler
    public void on(AccountDebited event) {
        AccountSummaryView view = repository.find(event.getAccountId());
        view.setAmount(view.getAmount().subtract(event.getAmount()));
        repository.save(view);
    }

    @EventHandler
    public void on(AccountCredited event) {
        AccountSummaryView view = repository.find(event.getAccountId());
        view.setAmount(view.getAmount().add(event.getAmount()));
        repository.save(view);
    }

    @QueryHandler
    public List<AccountSummaryView> handle(AllAccountSummaryQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public AccountSummaryView handle(SingleAccountSummaryQuery query) {
        return repository.find(query.getAccountId());
    }
}
