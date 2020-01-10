package com.sethkraut.bank.read;

import java.util.List;

public interface AccountSummaryRepository {
    AccountSummaryView find(String accountId);

    void save(AccountSummaryView accountSummaryView);

    List<AccountSummaryView> findAll();
}
