package com.sethkraut.bank.read;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountSummaryRepositoryImpl implements AccountSummaryRepository {
    private final Map<String, AccountSummaryView> data = new HashMap<>();

    @Override
    public AccountSummaryView find(String accountId) {
        return data.get(accountId);
    }

    @Override
    public void save(AccountSummaryView accountSummaryView) {
        data.put(accountSummaryView.getAccountId(), accountSummaryView);
    }

    @Override
    public List<AccountSummaryView> findAll() {
        return new ArrayList(data.values());
    }
}
