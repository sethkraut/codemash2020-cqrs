package com.sethkraut.bank.api;

import com.sethkraut.bank.read.AccountSummaryView;
import com.sethkraut.bank.read.AllAccountSummaryQuery;
import com.sethkraut.bank.read.SingleAccountSummaryQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountReadController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<AccountSummaryView> accounts() throws ExecutionException, InterruptedException {
        return (List<AccountSummaryView>) queryGateway.query(new AllAccountSummaryQuery(), new MultipleInstancesResponseType(AccountSummaryView.class)).get();
    }

    @GetMapping("/{id}")
    public AccountSummaryView account(@PathVariable String id) throws ExecutionException, InterruptedException {
        return queryGateway.query(new SingleAccountSummaryQuery(id), AccountSummaryView.class).get();
    }
}
