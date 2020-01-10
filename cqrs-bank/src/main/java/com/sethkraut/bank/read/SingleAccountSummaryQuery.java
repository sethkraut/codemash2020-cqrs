package com.sethkraut.bank.read;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleAccountSummaryQuery {
    private String accountId;
}
