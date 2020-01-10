package com.sethkraut.bank.api;

import com.sethkraut.bank.write.account.OpenAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountWriteController {
    private final CommandGateway commandGateway;

    @PostMapping
    public void createShowing(@RequestBody CreateAccount createAccount) {
        commandGateway.sendAndWait(new OpenAccount(UUID.randomUUID().toString(), createAccount.getAmount()));
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class CreateAccount {
        private BigDecimal amount;
    }
}
